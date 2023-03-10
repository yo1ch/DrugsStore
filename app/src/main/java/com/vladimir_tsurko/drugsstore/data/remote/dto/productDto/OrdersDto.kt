package com.vladimir_tsurko.drugsstore.data.remote.dto.productDto

data class OrdersDto(
    val id: Int,
    val listItemDto: List<ItemDto>,
    val place: String,
    val status: String,
    val userId: Int
)