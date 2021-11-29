package com.example.numbersgame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycled_guess.view.*

class GuessAdapter(val context: Context, val userGuess: List<String>): RecyclerView.Adapter<GuessAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycled_guess, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var recycledContent = userGuess[position]
        holder.itemView.apply {
            guess.text = recycledContent
        }
    }

    override fun getItemCount() = userGuess.size

}