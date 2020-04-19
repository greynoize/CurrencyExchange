package com.greynoize.base.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greynoize.base.R
import com.greynoize.base.databinding.ItemCurrencyBinding
import com.greynoize.base.ui.main.MainViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel

//  You can change the viewModel to a callback, but, because we don't reuse this adapter, here can be the the vm
class MainCurrenciesAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<MainCurrenciesAdapter.ViewHolder>() {
    var items = arrayListOf<CurrencyUIModel>()
    set(value) {
        field.clear()
        field.addAll(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCurrencyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_currency, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.item = item
        holder.binding.viewModel = viewModel
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(var binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root)
}