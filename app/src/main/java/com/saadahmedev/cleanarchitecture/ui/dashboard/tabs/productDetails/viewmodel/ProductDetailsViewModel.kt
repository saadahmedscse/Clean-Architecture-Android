package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.productDetails.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saadahmedev.cleanarchitecture.domain.model.product.Product
import com.saadahmedev.cleanarchitecture.domain.useCase.product.ProductDetailsUseCase
import com.saadahmedev.cleanarchitecture.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val productDetailsUseCase: ProductDetailsUseCase) : ViewModel() {

    private val _product = MutableStateFlow(ProductState())
    val product: StateFlow<ProductState> = _product

    fun getProduct(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        productDetailsUseCase.invoke(id).collect {
            Log.d("product_debug", "invoke: initiated")
            when (it) {
                is ResponseState.Loading -> {
                    _product.value = ProductState(isLoading = true)
                }

                is ResponseState.Success -> {
                    Log.d("product_debug", "invoke: ${it.data?.title}")
                    _product.value = ProductState(product = it.data)
                }

                is ResponseState.Error -> {
                    _product.value = ProductState(error = it.message!!)
                }
            }
        }
    }
}