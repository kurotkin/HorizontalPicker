package com.example.slider

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class SliderItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    val tvItem: TextView? = itemView?.findViewById(R.id.tv_item)
}