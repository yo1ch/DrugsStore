package com.vladimir_tsurko.drugsstore.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import com.vladimir_tsurko.drugsstore.domain.models.OrdersModel
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel
import com.vladimir_tsurko.drugsstore.domain.usecases.*
import com.vladimir_tsurko.drugsstore.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val makeOrderUseCase: MakeOrderUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
) : ViewModel() {


    private var _productsResponse = MutableLiveData<Resource<List<ProductModel>>?>()
    val productsResponse: LiveData<Resource<List<ProductModel>>?>
        get() = _productsResponse

    private var _categories = MutableLiveData<Resource<List<CategoryModel>>?>()
    val categories: LiveData<Resource<List<CategoryModel>>?>
        get() = _categories

    private var _cartProducts = MutableLiveData<MutableSet<PurchaseItemModel>?>()
    val cartProducts: LiveData<MutableSet<PurchaseItemModel>?>
        get() = _cartProducts

    private var _orders = MutableLiveData<List<OrdersModel>?>()
    val orders: LiveData<List<OrdersModel>?>
        get() = _orders


    init {
        getAllCategories()
        getAllProducts()
    }


    fun getOrders() = viewModelScope.launch{
        _orders.value = getOrdersUseCase()
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

    fun makeOrder(address: String) = viewModelScope.launch {
        if (_cartProducts.value != null) {
            val orderDetails = _cartProducts.value!!
            makeOrderUseCase(orderDetails.toList(), address)
            _cartProducts.value = null
        }
    }

    fun addToCart(productId: Int, productName: String) {
        val oldList = _cartProducts.value
        if (oldList != null && oldList.filter { it.name == productName }.isEmpty()) {
            val cartList = _cartProducts.value
            cartList?.add(
                PurchaseItemModel(
                    id = productId,
                    name = productName,
                    count = 1,
                )
            )
            _cartProducts.value = cartList
        } else {
            _cartProducts.value = mutableSetOf(
                PurchaseItemModel(
                    id = productId,
                    name = productName,
                    count = 1,
                )
            )
        }
    }

    fun increaseCount(product: PurchaseItemModel) {

        val oldSet = _cartProducts.value?.map {
            it.deepCopy()
        }
        oldSet?.forEach {
            if (it.id == product.id) {
                it.count = it.count + 1
            }
        }
        _cartProducts.value = oldSet?.toMutableSet()


    }

    fun decreaseCount(product: PurchaseItemModel) {
        val oldSet = _cartProducts.value?.map {
            it.deepCopy()
        }
        oldSet?.forEach {
            if (it.id == product.id) {
                if (it.count == 1) {
                    val newSet = oldSet.toMutableList()
                    newSet.remove(it)
                    _cartProducts.value = newSet.toMutableSet()
                } else {
                    it.count = it.count - 1
                    _cartProducts.value = oldSet.toMutableSet()
                }

            }
        }


    }

    fun logout() {
        logoutUseCase()
    }


}