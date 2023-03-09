package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.domain.Repository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke() = repository.logout()

}