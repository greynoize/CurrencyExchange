package com.greynoize.base.repository.network.base

import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> getApiResult(call: suspend () -> Response<T>): Result<T> {
        val response = call.invoke()

        return if (response.isSuccessful)
            Result.Success(response.body()!!)
        else
            Result.Fail(response.errorBody()!!)
    }
}