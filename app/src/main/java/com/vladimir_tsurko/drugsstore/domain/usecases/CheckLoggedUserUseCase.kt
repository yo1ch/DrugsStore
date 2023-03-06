package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugstore.domain.Repository
import javax.inject.Inject

class CheckLoggedUserUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Boolean = repository.checkLoggedUser()
}