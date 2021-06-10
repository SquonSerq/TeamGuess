package com.example.teamguess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameCycleRecyclerAdapter(var names: List<String>) : RecyclerView.Adapter<GameCycleRecyclerAdapter.GameCycleViewHolder>(){

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    class GameCycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerElement = itemView.findViewById<TextView>(R.id.recyclerViewRow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCycleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return GameCycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameCycleViewHolder, position: Int) {
        val element = names[position]
        holder.recyclerElement.text = element

        holder.itemView.setOnClickListener{
            itemClickListener?.invoke(position, element)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
}