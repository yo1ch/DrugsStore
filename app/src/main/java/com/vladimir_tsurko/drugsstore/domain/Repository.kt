package com.vladimir_tsurko.drugsstore.domain

import com.bumptech.glide.load.engine.Resource
import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.domain.models.RegistrationModel


interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): Resource<AuthResponseDto>

    suspend fun login(loginModel: LoginModel): Resource<AuthResponseDto>

    suspend fun getAllProducts(): Resource<List<ProductDto>>

    fun checkLoggedUser(): Boolean

}