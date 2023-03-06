package com.vladimir_tsurko.drugsstore.domain

import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugstore.data.remote.dto.productDto.ProductListDto
import com.vladimir_tsurko.drugstore.domain.models.LoginModel
import com.vladimir_tsurko.drugstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugstore.utils.Resource
import retrofit2.Response

interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto>

    suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto>

    suspend fun getAllProducts(): Resource<List<ProductDto>>

    fun checkLoggedUser(): Boolean

}