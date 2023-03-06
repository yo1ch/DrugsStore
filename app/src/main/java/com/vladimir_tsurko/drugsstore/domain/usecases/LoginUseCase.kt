package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugstore.domain.Repository
import com.vladimir_tsurko.drugstore.domain.models.LoginModel
import com.vladimir_tsurko.drugstore.utils.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {

    operator suspend fun invoke(loginModel: LoginModel): Resource<AuthResponseDto> = repository.login(loginModel)

}