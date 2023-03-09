package com.vladimir_tsurko.drugsstore.data.mappers

import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.CategoriesDtoItem
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import javax.inject.Inject

class ProductsMapper @Inject constructor() {

    fun mapCategoriesDtoToModelList(categoriesDto: List<CategoriesDtoItem>): List<CategoryModel>{
        val resultList = mutableListOf<CategoryModel>()
        categoriesDto.forEach {
            resultList.add(CategoryModel(
                id = it.id,
                name = it.name,
            ))
        }
        return resultList
    }


}