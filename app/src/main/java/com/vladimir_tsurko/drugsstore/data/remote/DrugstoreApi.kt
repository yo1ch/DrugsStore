package com.vladimir_tsurko.drugsstore.data.remote

import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.RegistrationDto
import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.LoginDto
import com.vladimir_tsurko.drugstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugstore.data.remote.dto.productDto.ProductListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface DrugstoreApi {

    @POST("auth/registration")
    suspend fun registerUser(@Body registrationDto: RegistrationDto): Response<AuthResponseDto>

    @POST("auth/login")
    suspend fun login(@Body loginDto: LoginDto): Response<AuthResponseDto>

    @GET("products")
    suspend fun getAllProducts(@Header("Authorization") token: String): Response<List<ProductDto>>


}