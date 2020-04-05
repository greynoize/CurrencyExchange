package com.greynoize.base.di

import com.greynoize.base.Const
import com.greynoize.base.repository.network.MovieRepository
import com.greynoize.base.repository.network.base.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

    single {
        provideApi(get())
    }
}

val networkModule = module {
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    single { MovieRepository(get()) }
}