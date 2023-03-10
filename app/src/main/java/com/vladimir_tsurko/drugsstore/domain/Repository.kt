package com.vladimir_tsurko.drugsstore.domain

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.domain.models.*
import com.vladimir_tsurko.drugsstore.utils.Resource


interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto>

    suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto>

    suspend fun getAllProducts(): Resource<List<ProductModel>>

    suspend fun getAllCategories(): Resource<List<CategoryModel>>

    suspend fun getProductsByCategory(categoryId: Int): Resource<List<ProductModel>>

    suspend fun makeOrder(purchaseModels: List<PurchaseItemModel>, address: String)

    suspend fun getOrders(): List<OrdersModel>

    fun logout()

    fun checkLoggedUser(): Boolean

}