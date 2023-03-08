package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(registrationModel: RegistrationModel): Resource<AuthResponseDto> = repository.registerUser(registrationModel)

}