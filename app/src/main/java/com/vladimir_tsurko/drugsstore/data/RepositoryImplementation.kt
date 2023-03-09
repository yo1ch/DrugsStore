package com.vladimir_tsurko.drugsstore.data

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vladimir_tsurko.drugsstore.data.mappers.AuthMapper
import com.vladimir_tsurko.drugsstore.data.mappers.ProductsMapper
import com.vladimir_tsurko.drugsstore.data.remote.DrugstoreApi
import com.vladimir_tsurko.drugsstore.data.remote.dto.ResponseError
import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.PurchaseDto
import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.*
import com.vladimir_tsurko.drugsstore.utils.Resource

import okhttp3.ResponseBody
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val drugstoreApi: DrugstoreApi,
    private val authMapper: AuthMapper,
    private val productsMapper: ProductsMapper,
    private val prefs: SharedPreferences,
): Repository {

    override suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto> {
        val response = drugstoreApi.registerUser(authMapper.mapRegistrationModelToDto(registrationModel))
        val resultResponse: Resource<AuthResponseDto>
        if(response.isSuccessful){
            resultResponse = Resource.Success(response.body()!!)
            saveLoggedUser(response.body()!!.token, response.body()!!.role)
        } else{
            val responseError = parseErrorBody(response.errorBody()!!)
            resultResponse =  Resource.Error("${responseError?.details}")
        }
        return resultResponse
    }

    override suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto> {
        val response = drugstoreApi.login(authMapper.mapLoginModelToDto(loginModel))
        val resultResponse: Resource<AuthResponseDto>
        if(response.isSuccessful){
            resultResponse = Resource.Success(response.body()!!)
            saveLoggedUser(response.body()!!.token, response.body()!!.role)
        } else{
            val responseError = parseErrorBody(response.errorBody()!!)
            resultResponse =  Resource.Error("${responseError?.details}")
        }
        return resultResponse
    }

    override suspend fun getAllProducts(): Resource<List<ProductModel>> {
        val resultResponse: Resource<List<ProductModel>>
        val response = drugstoreApi.getAllProducts()
        resultResponse = if(response.isSuccessful){
            Resource.Success(productsMapper.mapProductDtoListToModelList(response.body()!!))
        } else {
            val responseError = parseErrorBody(response.errorBody()!!)
            Resource.Error("${responseError?.details}")
        }
        return resultResponse
    }

    override suspend fun getProductsByCategory(categoryId: Int): Resource<List<ProductModel>> {
        val resultResponse: Resource<List<ProductModel>>
        val response = drugstoreApi.getProductsByCategory(categoryId)
        resultResponse = if(response.isSuccessful){
            Resource.Success(productsMapper.mapProductDtoListToModelList(response.body()!!))
        } else {
            val responseError = parseErrorBody(response.errorBody()!!)
            Resource.Error("${responseError?.details}")
        }
        return resultResponse
    }

    override suspend fun makeOrder(purchaseModels: List<PurchaseModel>, address:String) {
        val purchaseItemDtoList = purchaseModels.map {
            productsMapper.mapPurchaseModelToPurchaseItemDto(it)
        }
        val purchaseDto = PurchaseDto(
            status = "OPEN",
            place = address,
            listItem = purchaseItemDtoList
        )
        val token = prefs.getString("TOKEN","")
        val bearer = "Bearer $token"
        drugstoreApi.makeOrder(purchaseDto, bearer)
    }

    override suspend fun getAllCategories(): Resource<List<CategoryModel>> {
        val resultResponse: Resource<List<CategoryModel>>
        val response = drugstoreApi.getAllCategories()
        resultResponse = if(response.isSuccessful){
            Resource.Success(productsMapper.mapCategoriesDtoToModelList(response.body()!!))
        } else {
            val responseError = parseErrorBody(response.errorBody()!!)
            Resource.Error("${responseError?.details}")
        }
        return resultResponse
    }



    override fun checkLoggedUser(): Boolean {
        val token = prefs.getString("TOKEN","")
        val role = prefs.getString("ROLE","")
        Log.d("Login", "$token $role")
        return token != "" && role != ""
    }

    private fun saveLoggedUser(token: String, role: String){
        prefs.edit().putString("TOKEN", token).putString("ROLE", role).apply()

    }

    override fun logout() {
        prefs.edit().clear().apply()
    }


    private fun parseErrorBody(responseErrorBody: ResponseBody): ResponseError?{
        val gson = Gson()
        val type = object : TypeToken<ResponseError>(){}.type
        return  gson.fromJson(responseErrorBody.charStream(), type)
    }

}