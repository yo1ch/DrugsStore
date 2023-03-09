package com.vladimir_tsurko.drugsstore.presentation.adapters.purchaseadapter

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseModel

object PurchaseDiffUtilCallback: DiffUtil.ItemCallback<PurchaseModel>() {

    override fun areItemsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
        return oldItem == newItem
    }
}