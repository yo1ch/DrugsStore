package com.vladimir_tsurko.drugsstore.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.drugstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugstore.domain.models.LoginModel
import com.vladimir_tsurko.drugstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugstore.domain.usecases.CheckLoggedUserUseCase
import com.vladimir_tsurko.drugstore.domain.usecases.LoginUseCase
import com.vladimir_tsurko.drugstore.domain.usecases.RegistrationUseCase
import com.vladimir_tsurko.drugstore.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val loginUseCase: LoginUseCase,
    private val checkLoggedUserUseCase: CheckLoggedUserUseCase,
): ViewModel() {

    private var _registrationResponse = MutableLiveData<Resource<AuthResponseDto>?>()
    val registrationResponse: LiveData<Resource<AuthResponseDto>?>
        get() = _registrationResponse

    private var _loginResponse = MutableLiveData<Resource<AuthResponseDto>?>()
    val loginResponse: LiveData<Resource<AuthResponseDto>?>
        get() = _loginResponse

    fun login(loginModel: LoginModel) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading()
        _loginResponse.value = loginUseCase(loginModel)
    }

    fun registrateUser(registrationModel: RegistrationModel) = viewModelScope.launch {
        _registrationResponse.value = Resource.Loading()
        _registrationResponse.value = registrationUseCase(registrationModel)

    }

    fun checkLoggedUser(): Boolean{
        return checkLoggedUserUseCase()
    }




}