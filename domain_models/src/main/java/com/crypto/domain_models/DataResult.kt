package com.crypto.domain_models

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Failure(val throwable: Throwable) : DataResult<Nothing>()
}