package com.saadahmedev.cleanarchitecture.util

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(): ResponseState<T>()
    class Success<T>(data: T?): ResponseState<T>(data)
    class Error<T>(message: String?): ResponseState<T>(message = message)
}
