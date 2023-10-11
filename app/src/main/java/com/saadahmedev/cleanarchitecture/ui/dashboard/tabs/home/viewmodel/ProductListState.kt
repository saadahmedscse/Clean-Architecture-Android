package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.home.viewmodel

import com.saadahmedev.cleanarchitecture.domain.model.product.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val productList: List<Product> = emptyList(),
    val error: String = ""
)