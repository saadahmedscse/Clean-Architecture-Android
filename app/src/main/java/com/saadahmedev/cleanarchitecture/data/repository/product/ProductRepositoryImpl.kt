package com.saadahmedev.cleanarchitecture.data.repository.product

import com.saadahmedev.cleanarchitecture.data.dto.product.ProductResponse
import com.saadahmedev.cleanarchitecture.data.dto.product.Products
import com.saadahmedev.cleanarchitecture.data.source.ProductApi
import com.saadahmedev.cleanarchitecture.domain.repository.product.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApi) : ProductRepository {

    override suspend fun getProducts(limit: Int): ProductResponse {
        return productApi.getProducts(limit)
    }

    override suspend fun getProduct(id: Int): Products {
        return productApi.getProduct(id)
    }
}