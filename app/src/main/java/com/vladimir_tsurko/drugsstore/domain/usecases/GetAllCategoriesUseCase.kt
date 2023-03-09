package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.domain.Repository
import javax.inject.Inject


class GetAllCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getAllCategories()

}