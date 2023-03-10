package com.vladimir_tsurko.drugsstore.domain.models

data class PurchaseModel(
    val listItem: List<PurchaseItemModel>,
    val place: String,
    val status: String,
)