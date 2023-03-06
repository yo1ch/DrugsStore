package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugstore.domain.Repository
import com.vladimir_tsurko.drugstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugstore.utils.Resource
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(registrationModel: RegistrationModel): Resource<AuthResponseDto> = repository.registerUser(registrationModel)

}