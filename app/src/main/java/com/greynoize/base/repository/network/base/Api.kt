package com.greynoize.base.repository.network.base

import com.greynoize.base.repository.model.currency.CurrencyResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("api/android/latest")
    suspend fun getCurrencies(@Query("base") base: String) : Response<CurrencyResponseModel>
}