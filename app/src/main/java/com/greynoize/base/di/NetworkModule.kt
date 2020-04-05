package com.greynoize.base.di

import com.greynoize.base.Const
import com.greynoize.base.repository.network.Api
import com.greynoize.base.repository.network.search.SearchRepo
import com.greynoize.base.repository.network.search.SearchRepoImplementation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val apiModule = module {
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

    single {
        provideApi(get())
    }
}

val networkModule = module {
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    fun provideSearchRepository(api: Api): SearchRepo = SearchRepoImplementation(api)

    single { provideSearchRepository(get()) }
}