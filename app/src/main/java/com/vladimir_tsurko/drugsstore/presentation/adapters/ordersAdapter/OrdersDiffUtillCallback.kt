package com.vladimir_tsurko.drugsstore.presentation.adapters.ordersAdapter

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.drugsstore.domain.models.OrdersModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseModel

object OrdersDiffUtillCallback: DiffUtil.ItemCallback<OrdersModel>() {

    override fun areItemsTheSame(oldItem: OrdersModel, newItem: OrdersModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrdersModel, newItem: OrdersModel): Boolean {
        return oldItem == newItem
    }
}