package com.guava.guavacake.ui

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guava.guavacake.features.driver.DriverListAdapter
import com.guava.guavacake.features.driver.model.Route

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.layoutManager = LinearLayoutManager(
        recyclerView.context,
        LinearLayoutManager.VERTICAL,
        false
    )
    recyclerView.adapter = adapter
}

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, items: List<Route>?) {
    if (items == null) return
    (recyclerView.adapter as DriverListAdapter?)?.let {
        it.items = items
    }
}