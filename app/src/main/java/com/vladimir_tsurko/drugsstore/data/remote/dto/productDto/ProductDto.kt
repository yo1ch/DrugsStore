package com.vladimir_tsurko.drugsstore.data.remote.dto.productDto

data class ProductDto(
    val categoryId: Int,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val specification: String
)