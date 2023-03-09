package com.vladimir_tsurko.drugsstore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseModel
import com.vladimir_tsurko.drugsstore.domain.usecases.GetAllCategoriesUseCase
import com.vladimir_tsurko.drugsstore.domain.usecases.GetAllProductsUseCase
import com.vladimir_tsurko.drugsstore.domain.usecases.GetProductsByCategoryUseCase
import com.vladimir_tsurko.drugsstore.domain.usecases.LogoutUseCase
import com.vladimir_tsurko.drugsstore.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
) : ViewModel() {


    private var _productsResponse = MutableLiveData<Resource<List<ProductModel>>?>()
    val productsResponse: LiveData<Resource<List<ProductModel>>?>
        get() = _productsResponse

    private var _categories = MutableLiveData<Resource<List<CategoryModel>>?>()
    val categories: LiveData<Resource<List<CategoryModel>>?>
        get() = _categories

    private var _cartProducts = MutableLiveData<MutableSet<PurchaseModel>?>()
    val cartProducts: LiveData<MutableSet<PurchaseModel>?>
        get() = _cartProducts


    init {
        getAllCategories()
        getAllProducts()
    }


    private fun getAllProducts() = viewModelScope.launch {
        _productsResponse.value = getAllProductsUseCase()
    }

    private fun getAllCategories() = viewModelScope.launch {
        _categories.value = getAllCategoriesUseCase()
    }

    fun getProductsByCategory(selectedCategoryId: Int) {
        viewModelScope.launch {
            _productsResponse.value = getProductsByCategoryUseCase(selectedCategoryId)
        }
    }

    fun addToCart(productId: Int, productName: String) {
        val oldList = _cartProducts.value
        if ( oldList != null && oldList.filter { it.name == productName }.isEmpty() ) {
            val cartList = _cartProducts.value
            cartList?.add(
                PurchaseModel(
                    id = productId,
                    name = productName,
                    count = 1,
                )
            )
            _cartProducts.value = cartList
        } else {
            _cartProducts.value = mutableSetOf(
                PurchaseModel(
                    id = productId,
                    name = productName,
                    count = 1,
                )
            )
        }
    }

    fun increaseCount(product: PurchaseModel){

        val oldSet = _cartProducts.value?.map {
            it.deepCopy()
        }
        oldSet?.forEach {
            if(it.id == product.id){
                it.count = it.count + 1
            }
        }
        _cartProducts.value = oldSet?.toMutableSet()


    }

    fun decreaseCount(product: PurchaseModel){
        val oldSet = _cartProducts.value?.map {
            it.deepCopy()
        }
        oldSet?.forEach {
            if(it.id == product.id){
                if(it.count == 1){
                    val newSet = oldSet.toMutableList()
                    newSet.remove(it)
                    _cartProducts.value = newSet.toMutableSet()
                } else{
                    it.count = it.count - 1
                    _cartProducts.value = oldSet?.toMutableSet()
                }

            }
        }



    }

    fun logout() {
        logoutUseCase()
    }


}