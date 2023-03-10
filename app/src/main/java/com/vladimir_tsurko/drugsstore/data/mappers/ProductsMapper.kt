package com.vladimir_tsurko.drugsstore.data.mappers

import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.CategoriesDtoItem
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.OrdersDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.PurchaseItemDto
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import com.vladimir_tsurko.drugsstore.domain.models.OrdersModel
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel
import javax.inject.Inject

class ProductsMapper @Inject constructor() {

    fun mapCategoriesDtoToModelList(categoriesDtoList: List<CategoriesDtoItem>): List<CategoryModel>{
        val resultList = mutableListOf<CategoryModel>()
        categoriesDtoList.forEach {
            resultList.add(CategoryModel(
                id = it.id,
                name = it.name,
            ))
        }
        return resultList
    }

    fun mapProductDtoListToModelList(productDtoList: List<ProductDto>): List<ProductModel>{
        val resultList = mutableListOf<ProductModel>()
        productDtoList.forEach {
            resultList.add(
                ProductModel(
                    categoryId = it.categoryId,
                    id = it.id,
                    imageUrl = it.imageUrl,
                    name = it.name,
                    price = it.price,
                    specification = it.specification,
                )
            )
        }
        return resultList
    }

    fun mapPurchaseModelToPurchaseItemDto(purchaseModel: PurchaseItemModel): PurchaseItemDto{
        return PurchaseItemDto(
            productId = purchaseModel.id.toString(),
            count = purchaseModel.count.toString()
        )
    }

    fun mapOrdersDtoToOrdersModel(ordersDto: OrdersDto): OrdersModel {
        return OrdersModel(
            id = ordersDto.id,
            place = ordersDto.place,
            userId = ordersDto.userId,
            status = ordersDto.status
        )
    }

}