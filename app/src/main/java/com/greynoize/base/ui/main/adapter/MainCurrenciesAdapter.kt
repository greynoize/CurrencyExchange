package com.greynoize.base.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greynoize.base.R
import com.greynoize.base.databinding.ItemCurrencyBinding
import com.greynoize.base.ui.main.MainViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel

//  You can change the viewModel to a callback, but, because we don't reuse this adapter, here can be the the vm
class MainCurrenciesAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<MainCurrenciesAdapter.ViewHolder>() {
    var items = mutableListOf<CurrencyUIModel>()
    set(value) {
        field.clear()

        value.forEach {
            field.add(it.copy())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCurrencyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_currency, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = items[position]

            binding.item = item
            binding.viewModel = viewModel

            binding.itemCurrencyInput.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus && position != 0) {
                    viewModel.onItemClick(item)
                }
            }

            val total = if (adapterPosition == 0) viewModel.count else item.total

            val editTextValue = String.format("%.2f", total)
            binding.itemCurrencyInput.setText(editTextValue)

            binding.itemCurrencyInput.addTextChangedListener {
                val stringValue = (it.toString())

                if (item.code == viewModel.baseCurrency && viewModel.count.toString() != stringValue && adapterPosition == 0) {
                    val doubleValue = if (stringValue.isEmpty()) 0.00 else stringValue.toDouble()
                    Log.d("TAAG", doubleValue.toString())

                    items[0].total = doubleValue
                    viewModel.updateCount(doubleValue)
                }
            }

            binding.executePendingBindings()
        }
    }
}