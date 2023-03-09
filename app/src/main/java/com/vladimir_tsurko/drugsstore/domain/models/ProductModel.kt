package com.vladimir_tsurko.drugsstore.domain.models

data class ProductModel(
    val categoryId: Int,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val specification: String
)