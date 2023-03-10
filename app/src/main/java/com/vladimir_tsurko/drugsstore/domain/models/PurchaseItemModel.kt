package com.vladimir_tsurko.drugsstore.domain.models

import com.google.gson.Gson

data class PurchaseItemModel(
    val id: Int,
    val name: String,
    var count: Int = 1,
){
    fun deepCopy() : PurchaseItemModel {
        return Gson().fromJson(Gson().toJson(this), this.javaClass)
    }
}