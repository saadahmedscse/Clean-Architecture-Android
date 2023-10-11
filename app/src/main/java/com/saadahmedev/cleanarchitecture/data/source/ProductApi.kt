package com.saadahmedev.cleanarchitecture.data.source

import com.saadahmedev.cleanarchitecture.data.dto.product.ProductResponse
import com.saadahmedev.cleanarchitecture.data.dto.product.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int): ProductResponse

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Products
}