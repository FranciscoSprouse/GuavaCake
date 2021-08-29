package com.guava.guavacake.features.driver

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.guava.guavacake.R
import com.guava.guavacake.databinding.ViewRouteItemBinding
import com.guava.guavacake.features.driver.model.Route

class DriverListAdapter: RecyclerView.Adapter<DriverListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewRouteItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    var items: List<Route> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_route_item
    }

    class ViewHolder(val binding: ViewRouteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Route) {
            binding.obj = item
            binding.executePendingBindings()
        }
    }
}