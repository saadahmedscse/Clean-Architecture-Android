package com.saadahmedev.cleanarchitecture.domain.repository.product

import com.saadahmedev.cleanarchitecture.data.dto.product.ProductResponse
import com.saadahmedev.cleanarchitecture.data.dto.product.Products

interface ProductRepository {

    suspend fun getProducts(limit: Int): ProductResponse

    suspend fun getProduct(id: Int): Products
}