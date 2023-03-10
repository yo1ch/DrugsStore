package com.vladimir_tsurko.drugsstore.presentation.adapters.purchaseadapter

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel

object PurchaseDiffUtilCallback: DiffUtil.ItemCallback<PurchaseItemModel>() {

    override fun areItemsTheSame(oldItem: PurchaseItemModel, newItem: PurchaseItemModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PurchaseItemModel, newItem: PurchaseItemModel): Boolean {
        return oldItem == newItem
    }
}