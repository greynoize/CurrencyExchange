package com.greynoize.base.ui.first

import android.os.Bundle
import android.view.View
import com.greynoize.base.R
import com.greynoize.base.ui.base.BaseFragment

class FirstScreenFragment: BaseFragment<FirstViewModel>() {
    override fun layout() = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewModel.getMovies()
    }

    companion object {
        fun newInstance() = FirstScreenFragment()
    }
}