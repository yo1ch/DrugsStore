package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(categoryId: Int): Resource<List<ProductModel>> = repository.getProductsByCategory(categoryId)

}