package com.vladimir_tsurko.drugsstore.domain

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.models.LoginModel
import com.vladimir_tsurko.drugsstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugsstore.utils.Resource


interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto>

    suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto>

    suspend fun getAllProducts(): Resource<List<ProductDto>>

    fun logout()

    fun checkLoggedUser(): Boolean

}