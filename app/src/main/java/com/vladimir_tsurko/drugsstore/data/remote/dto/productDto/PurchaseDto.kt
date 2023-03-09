package com.vladimir_tsurko.drugsstore.data.remote.dto.productDto

data class PurchaseDto(
    val listItem: List<PurchaseItemDto>,
    val place: String,
    val status: String = "OPEN"
)