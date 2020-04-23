package com.greynoize.base.repository.network.base

sealed class Result<T> {
    data class Success<T>(val value: T): Result<T>()
    data class Fail<T>(val value: String): Result<T>()
}