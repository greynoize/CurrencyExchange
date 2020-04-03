package com.greynoize.base.repository.network

import retrofit2.Response

class BaseRepository {
    suspend fun <T : Any> getApiResult(call: suspend () -> Response<T>): Result<T> {
        val response = call.invoke()

        return if (response.isSuccessful)
            Result.Success(response.body()!!)
        else
            Result.Fail(response.errorBody()!!
        )
    }
}