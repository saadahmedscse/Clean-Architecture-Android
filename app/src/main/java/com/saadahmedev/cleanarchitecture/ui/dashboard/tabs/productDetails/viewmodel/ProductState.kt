package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.productDetails.viewmodel

import com.saadahmedev.cleanarchitecture.domain.model.product.ProductDetail

data class ProductState(
    val isLoading: Boolean = false,
    val product: ProductDetail? = null,
    val error: String = ""
)