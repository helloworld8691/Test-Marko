package com.sts.test_marko.Utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sts.test_marko.ui.pickingList.PickingListAdapter
import com.sts.test_marko.ui.pickingList.ProductListAdapter

@BindingAdapter("pickingListAdapter")
fun RecyclerView.setPickingListAdapter(adapter: PickingListAdapter) {
    layoutManager = LinearLayoutManager(context)
    setAdapter(adapter)
}

@BindingAdapter("imageUrl")
fun ImageView.setImageByUrl(imageUrl : String){
    Glide.with(context).load(imageUrl).into(this)
}
