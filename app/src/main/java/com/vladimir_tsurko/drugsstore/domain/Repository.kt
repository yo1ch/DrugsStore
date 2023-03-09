package com.vladimir_tsurko.drugsstore.domain

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.models.*
import com.vladimir_tsurko.drugsstore.utils.Resource
import retrofit2.Response
import retrofit2.http.Path


interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto>

    suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto>

    suspend fun getAllProducts(): Resource<List<ProductModel>>

    suspend fun getAllCategories(): Resource<List<CategoryModel>>

    suspend fun getProductsByCategory(categoryId: Int): Resource<List<ProductModel>>

    suspend fun makeOrder(purchaseModels: List<PurchaseModel>, address: String)

    fun logout()

    fun checkLoggedUser(): Boolean

}