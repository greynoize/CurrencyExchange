package com.greynoize.base.repository.network

import okhttp3.ResponseBody
import java.lang.Exception

sealed class Result<T> {
    data class Success<T>(val value: T): Result<T>()
    data class Fail<T>(val value: ResponseBody): Result<T>()
}