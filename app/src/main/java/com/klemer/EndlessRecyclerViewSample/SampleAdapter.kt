package com.klemer.EndlessRecyclerViewSample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.klemer.EndlessRecyclerViewSample.databinding.ItemListBinding

class SampleAdapter :  RecyclerView.Adapter<SampleViewHolder>() {
    private var currentList = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false).apply {
            return SampleViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    fun update(newList : MutableList<String>){
        currentList.addAll(newList)
        notifyDataSetChanged()
    }
}

class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val binding = ItemListBinding.bind(itemView)

    fun bind(name: String){
        binding.tvPersonName.text = name
    }
}