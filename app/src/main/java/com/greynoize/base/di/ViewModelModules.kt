package com.greynoize.base.di

import com.greynoize.base.ui.main.MainFragment
import com.greynoize.base.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}