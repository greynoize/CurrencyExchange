package com.greynoize.base.di

import com.greynoize.base.Const
import com.greynoize.base.repository.network.base.CurrenciesApi
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.repository.network.repositories.CurrencyRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun provideExchangeApi(): CurrenciesApi {
        val client = createOkHttpBuilder().build()
        val retrofit = createRetrofit(client, Const.BASE_URL)
        return retrofit.create(CurrenciesApi::class.java)
    }

    single { provideExchangeApi() }
}

val repositoryModule = module {
    single {
        provideCurrencyRepository(get())
    }
}

fun provideCurrencyRepository(currenciesApi: CurrenciesApi): CurrencyRepository {
    return CurrencyRepositoryImpl(currenciesApi)
}

fun createRetrofit(client: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createOkHttpBuilder(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
}