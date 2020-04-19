package com.greynoize.base

import android.app.Application
import com.greynoize.base.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(apiModule, viewModelModule, repositoryModule))
        }
    }
}