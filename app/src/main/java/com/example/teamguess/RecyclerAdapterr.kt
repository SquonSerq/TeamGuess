package com.example.teamguess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterr (var texts: List<String>) : RecyclerView.Adapter<RecyclerAdapterr.RecyclerAdapterrHolder>() {

    class RecyclerAdapterrHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerRow = itemView.findViewById<TextView>(R.id.recyclerViewRow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterrHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return RecyclerAdapterrHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterrHolder, position: Int) {
        val element = texts[position]
        holder.recyclerRow.text = element
    }

    override fun getItemCount(): Int {
        return texts.size
    }


}