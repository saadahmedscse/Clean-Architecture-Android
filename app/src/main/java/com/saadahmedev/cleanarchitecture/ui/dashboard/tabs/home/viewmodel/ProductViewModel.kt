package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saadahmedev.cleanarchitecture.domain.useCase.product.ProductListUseCase
import com.saadahmedev.cleanarchitecture.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productListUseCase: ProductListUseCase) : ViewModel() {

    private val _productList = MutableStateFlow(ProductListState())
    val productList: StateFlow<ProductListState> = _productList

    fun getProducts(limit: Int) = viewModelScope.launch(Dispatchers.IO) {
        productListUseCase.invoke(limit).collect {
            when (it) {
                is ResponseState.Loading -> {
                    _productList.value = ProductListState(isLoading = true)
                }

                is ResponseState.Success -> {
                    _productList.value = ProductListState(productList = it.data?: emptyList())
                }

                is ResponseState.Error -> {
                    _productList.value = ProductListState(error = it.message!!)
                }
            }
        }
    }
}