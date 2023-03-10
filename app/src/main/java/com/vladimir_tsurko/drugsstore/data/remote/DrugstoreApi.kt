package com.vladimir_tsurko.drugsstore.data.remote

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.LoginDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.RegistrationDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.CategoriesDtoItem
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.OrdersDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.PurchaseDto
import retrofit2.Response
import retrofit2.http.*


interface DrugstoreApi {

    @POST("auth/registration")
    suspend fun registerUser(@Body registrationDto: RegistrationDto): Response<AuthResponseDto>

    @POST("auth/login")
    suspend fun login(@Body loginDto: LoginDto): Response<AuthResponseDto>

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductDto>>

    @GET("category")
    suspend fun getAllCategories(): Response<List<CategoriesDtoItem>>

    @GET("products/by-category/{id}")
    suspend fun getProductsByCategory(@Path("id") id: Int):Response<List<ProductDto>>

    @POST("purchases")
    suspend fun makeOrder(
        @Body purchaseDto: PurchaseDto,
        @Header("Authorization") token: String,
    )

    @GET("purchases/find_all_by_current_user")
    suspend fun getOrders(
        @Header("Authorization") token: String,
    ): List<OrdersDto>


}