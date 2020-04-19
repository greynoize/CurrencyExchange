package com.greynoize.base.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greynoize.base.R
import com.greynoize.base.databinding.ItemCurrencyBinding
import com.greynoize.base.ui.model.currency.CurrencyUIModel

class MainCurrenciesAdapter(private val onClickCallback: (CurrencyUIModel) -> Unit) : RecyclerView.Adapter<MainCurrenciesAdapter.ViewHolder>() {
    var items = mutableListOf<CurrencyUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCurrencyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_currency, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.item = item
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            onClickCallback(item)
        }
    }

    inner class ViewHolder(var binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root)
}