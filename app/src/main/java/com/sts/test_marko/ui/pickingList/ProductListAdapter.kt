package com.sts.test_marko.ui.pickingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sts.test_marko.databinding.ItemProductBinding
import com.sts.test_marko.model.PickingOrderModel

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){

    var allData = ArrayList<PickingOrderModel.OrderItem>()

    fun loadData(allData : List<PickingOrderModel.OrderItem>){
        this.allData.clear()
        this.allData.addAll(allData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allData[position].product)
    }

    override fun getItemCount(): Int = allData.size

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(productItem : PickingOrderModel.Product){
            binding.apply {
                setProductItem(productItem)
                executePendingBindings()
            }
        }
    }
}