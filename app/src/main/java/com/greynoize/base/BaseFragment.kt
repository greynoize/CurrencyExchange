package com.greynoize.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    protected abstract fun layout(): Int

}