package com.vladimir_tsurko.drugsstore.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.drugsstore.data.remote.dto.productDto.ProductDto
import com.vladimir_tsurko.drugsstore.domain.usecases.GetAllProductsUseCase
import com.vladimir_tsurko.drugsstore.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {


    private var _productsResponse = MutableLiveData<Resource<List<ProductDto>>?>()
    val productsResponse: LiveData<Resource<List<ProductDto>>?>
        get() = _productsResponse

    init{
        getAllProducts()
    }


    private fun getAllProducts() = viewModelScope.launch {
        _productsResponse.value = getAllProductsUseCase()
    }


}