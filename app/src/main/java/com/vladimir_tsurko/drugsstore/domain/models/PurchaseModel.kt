package com.vladimir_tsurko.drugsstore.domain.models

import com.google.gson.Gson

data class PurchaseModel(
    val id: Int,
    val name: String,
    var count: Int = 1,
){
    fun deepCopy() : PurchaseModel {
        return Gson().fromJson(Gson().toJson(this), this.javaClass)
    }
}