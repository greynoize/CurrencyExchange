package com.greynoize.base.ui.first

import com.greynoize.base.R
import com.greynoize.base.ui.base.BaseFragment

class FirstScreenFragment: BaseFragment<FirstViewModel>() {
    override fun layout() = R.layout.fragment_first

    companion object {
        fun newInstance() = FirstScreenFragment()
    }
}