package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Resource<List<ProductDto>> = repository.getAllProducts()
}