package com.vladimir_tsurko.drugsstore.domain.models


data class OrdersModel(
    val id: Int,
    val place: String,
    val status: String,
    val userId: Int
)