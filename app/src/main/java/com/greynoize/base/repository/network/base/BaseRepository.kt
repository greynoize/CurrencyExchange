package com.greynoize.base.repository.network.base

import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> getApiResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call.invoke()

            return if (response.isSuccessful)
                Result.Success(response.body()!!)
            else
                Result.Fail(response.errorBody().toString())
        } catch (e: Exception) {
            return Result.Fail(e.toString())
        }
    }
}