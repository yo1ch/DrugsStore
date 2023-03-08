package com.vladimir_tsurko.drugsstore.data.mappers

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.LoginDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.RegistrationDto
import com.vladimir_tsurko.drugsstore.domain.models.LoginModel
import com.vladimir_tsurko.drugsstore.domain.models.RegistrationModel
import javax.inject.Inject

class AuthMapper @Inject constructor(
) {

    fun mapRegistrationModelToDto(registrationModel: RegistrationModel): RegistrationDto {
        return RegistrationDto(
            name = registrationModel.name,
            password = registrationModel.password,
            surname = registrationModel.surname,
            username = registrationModel.username
        )
    }

    fun mapLoginModelToDto(loginModel: LoginModel): LoginDto {
        return LoginDto(
            username = loginModel.username,
            password = loginModel.password,
        )
    }


}