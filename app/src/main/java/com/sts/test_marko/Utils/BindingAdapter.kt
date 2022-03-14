package com.sts.test_marko.Utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sts.test_marko.ui.pickingList.PickingListAdapter
import com.sts.test_marko.ui.pickingList.ProductListAdapter

@BindingAdapter("pickingListAdapter")
fun RecyclerView.setPickingListAdapter(adapter: PickingListAdapter) {
    layoutManager = LinearLayoutManager(context)
    setAdapter(adapter)
}

@BindingAdapter("productListAdapter")
fun RecyclerView.setProductListAdapter(adapter: ProductListAdapter) {
    layoutManager = LinearLayoutManager(context)
    setAdapter(adapter)
}
