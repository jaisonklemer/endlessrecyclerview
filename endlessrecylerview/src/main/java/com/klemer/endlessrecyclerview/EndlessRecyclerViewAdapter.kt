package com.klemer.endlessrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

internal class EndlessRecyclerViewAdapter(@LayoutRes private val customView: Int?) :
    RecyclerView.Adapter<EndlessRecyclerViewVH>() {
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EndlessRecyclerViewVH {
        val view = customView ?: R.layout.loading
        LayoutInflater.from(parent.context).inflate(view, parent, false).apply {
            return EndlessRecyclerViewVH(this)
        }
    }

    override fun onBindViewHolder(holder: EndlessRecyclerViewVH, position: Int) {
        holder.bind(isLoading)
    }

    override fun getItemCount() = 1

    fun shouldLoading(loading: Boolean) {
        isLoading = loading
        notifyDataSetChanged()
    }
}

internal class EndlessRecyclerViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(showProgress: Boolean = false) {
        itemView.rootView.visibility = if (showProgress) View.VISIBLE else View.GONE
    }
}