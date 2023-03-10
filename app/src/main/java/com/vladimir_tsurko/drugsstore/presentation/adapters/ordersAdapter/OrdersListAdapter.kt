package com.vladimir_tsurko.drugsstore.presentation.adapters.ordersAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladimir_tsurko.drugsstore.databinding.ItemOrderListBinding
import com.vladimir_tsurko.drugsstore.domain.models.OrdersModel

class OrdersListAdapter : ListAdapter<OrdersModel, OrdersViewHolder>(OrdersDiffUtillCallback) {

    var test = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemOrderListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val order = getItem(position)
        test = order.id
        holder.binding.adress.text = order.place
        holder.binding.id.text = "Заказ №${order.id}"
        if (order.status == "OPEN"){
            holder.binding.status.setTextColor(Color.parseColor("#5CFA91"))
            holder.binding.status.text = "АКТИВЕН"
        } else {
            holder.binding.status.setTextColor(Color.parseColor("#FA5C7C"))
            holder.binding.status.text = "ЗАКРЫТ"
        }
    }
}
