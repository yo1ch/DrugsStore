package com.vladimir_tsurko.drugsstore.domain.usecases


import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.LoginModel
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {

    operator suspend fun invoke(loginModel: LoginModel): Resource<AuthResponseDto> = repository.login(loginModel)

}