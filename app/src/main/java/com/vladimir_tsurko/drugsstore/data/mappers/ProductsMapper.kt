package com.vladimir_tsurko.drugsstore.data.mappers

import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.CategoriesDtoItem
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
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

}