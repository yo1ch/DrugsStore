package com.vladimir_tsurko.drugsstore.presentation.adapters.purchaseadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladimir_tsurko.drugsstore.databinding.ItemPurchaseListBinding
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseModel

class PurchaseListAdapter : ListAdapter<PurchaseModel, PurchaseViewHolder>(PurchaseDiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding = ItemPurchaseListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.name.text = product.name
    }
}
