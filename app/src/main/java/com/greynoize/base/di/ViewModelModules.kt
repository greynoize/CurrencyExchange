package com.greynoize.base.di

import com.greynoize.base.ui.first.FirstViewModel
import com.greynoize.base.ui.second.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstViewModel() }
    viewModel { SecondViewModel() }
}