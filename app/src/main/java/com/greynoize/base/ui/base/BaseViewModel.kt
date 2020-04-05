package com.greynoize.base.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greynoize.base.ui.base.navigation.Location

open class BaseViewModel: ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Location>()
}