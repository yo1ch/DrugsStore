package com.vladimir_tsurko.drugsstore.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel

object CatalogDiffUtilCallback: DiffUtil.ItemCallback<ProductModel>() {

    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem == newItem
    }
}