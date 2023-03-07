package com.vladimir_tsurko.drugstore.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.drugstore.data.remote.dto.productDto.ProductDto

object CatalogDiffUtilCallback: DiffUtil.ItemCallback<ProductDto>() {

    override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem == newItem
    }
}