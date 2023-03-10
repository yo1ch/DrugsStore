package com.vladimir_tsurko.drugsstore.presentation.adapters

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.vladimir_tsurko.drugsstore.databinding.ItemProductListBinding
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel

class ProductListAdapter: ListAdapter<ProductModel, ProductListViewHolder>(CatalogDiffUtilCallback) {


    var onButtonClickListener: ((View, ProductModel) -> Unit)? = null

    var cartProducts = listOf<PurchaseItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = getItem(position)
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.binding.imageView)
        holder.binding.name.text = product.name
        holder.binding.specification.text = product.specification
        holder.binding.price.text = "${product.price}р"
        holder.binding.addToCartButton.text
        holder.binding.addToCartButton.setOnClickListener {
            onButtonClickListener?.invoke(it, product)
        }
        if(cartProducts.any { it.id == product.id }){
            with(holder.binding.addToCartButton){
                isClickable = false
                setBackgroundColor(Color.WHITE)
                text = "Товар добавлен в корзину"
                setTextColor(Color.BLACK)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)
            }
        }
    }
}