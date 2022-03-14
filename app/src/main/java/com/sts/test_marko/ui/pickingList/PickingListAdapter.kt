package com.sts.test_marko.ui.pickingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sts.test_marko.databinding.ItemPickingBinding
import com.sts.test_marko.model.PickingOrderModel

class PickingListAdapter : RecyclerView.Adapter<PickingListAdapter.ViewHolder>() {

    private val allData : MutableList<PickingOrderModel> = ArrayList()

    fun loadData(allData : List<PickingOrderModel>) {
        this.allData.clear()
        this.allData.addAll(allData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPickingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allData[position])
    }

    override fun getItemCount(): Int = allData.size


    inner class ViewHolder(private val binding: ItemPickingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pickingOrderItem : PickingOrderModel){
            binding.apply {

                productListAdapter?.loadData(pickingOrderItem.items)

                setPickingOrderItem(pickingOrderItem)
                executePendingBindings()
            }
        }
    }
}