package com.saadahmedev.cleanarchitecture.domain.useCase.product

import com.saadahmedev.cleanarchitecture.domain.model.product.Product
import com.saadahmedev.cleanarchitecture.domain.repository.product.ProductRepository
import com.saadahmedev.cleanarchitecture.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {

    operator fun invoke(limit: Int): Flow<ResponseState<List<Product>>> = flow {
        try {
            emit(ResponseState.Loading())

            val products = productRepository.getProducts(limit).products.map {
                it.toProduct()
            }

            emit(ResponseState.Success(products))
        }
        catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage?: "Unexpected error occurred"))
        }
    }
}