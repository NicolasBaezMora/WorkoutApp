package com.example.workoutapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemMotivationBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responsemodels.MotivationElementResponse
import com.squareup.picasso.Picasso

class MotivationAdapter: RecyclerView.Adapter<MotivationAdapter.MotivationViewHolder>() {

    var listElements = emptyList<MotivationElementResponse>()

    inner class MotivationViewHolder(itemViewMotivation: View): RecyclerView.ViewHolder(itemViewMotivation) {

        private val itemMotivationBinding = ItemMotivationBinding.bind(itemViewMotivation)

        fun bind(itemMotivation: MotivationElementResponse){
            Picasso.get().load(itemMotivation.imageUrl).into(itemMotivationBinding.imageViewMotivation)
            itemMotivationBinding.textViewPhrase.text = itemMotivation.phrase
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotivationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_motivation, parent, false)
        return MotivationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MotivationViewHolder, position: Int) {
        holder.bind(listElements[position])
    }

    override fun getItemCount(): Int = listElements.size


}