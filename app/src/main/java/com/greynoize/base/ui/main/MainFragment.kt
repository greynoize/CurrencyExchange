package com.greynoize.base.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.greynoize.base.R
import com.greynoize.base.databinding.FragmentMainBinding
import com.greynoize.base.ui.base.BaseFragment
import com.greynoize.base.ui.base.KeyboardUtils
import com.greynoize.base.ui.main.adapter.MainAdapter
import com.greynoize.base.ui.main.adapter.MainDiffUtilCallback
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {
    override val layout = R.layout.fragment_main
    override val fragmentViewModel by viewModel<MainViewModel>()

    private lateinit var adapter: MainAdapter

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        val binding = getBinding(inflater, container) as FragmentMainBinding
        binding.mainViewModel = fragmentViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initViewModelData()
    }

    override fun onResume() {
        super.onResume()
        fragmentViewModel.requestCurrencies()
    }

    override fun onPause() {
        super.onPause()
        fragmentViewModel.cancelRequest(true) // cancels request in pause
    }

    private fun initViewModelData() {
        fragmentViewModel.currenciesList.observe(viewLifecycleOwner, Observer {
            val data = it ?: return@Observer

            val diffUtilCallback = MainDiffUtilCallback(adapter.items, it)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)

            adapter.items = data
            diffUtilResult.dispatchUpdatesTo(adapter)

            if (fragmentViewModel.positionChanged) {
                fragmentViewModel.positionChanged = false
                main_list.scrollToPosition(0)
            }
        })
    }

    private fun initUI() {
        adapter = MainAdapter(fragmentViewModel)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        main_list.adapter = adapter
        main_list.layoutManager = layoutManager
        (main_list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        main_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findFirstVisibleItemPosition() > 0) {
                    KeyboardUtils.hideKeyboard(main_list)
                }
            }
        })
    }
}