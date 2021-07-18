package com.example.workoutapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responses.ExerciseElementResponse

class ExerciseAdapter(
        private val onItemClick: OnItemClick
): RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    companion object {
        var listElements = listOf<ExerciseElementResponse>()
    }

    inner class ExerciseViewHolder(itemViewExercise: View): RecyclerView.ViewHolder(itemViewExercise){

        private val exerciseItemBinding = ItemExerciseBinding.bind(itemViewExercise)

        fun bind(itemExercise: ExerciseElementResponse){
            exerciseItemBinding.textViewTitleExercise.text = itemExercise.title
            exerciseItemBinding.cardViewItemExercise.setOnClickListener{
                onItemClick.onItemClickListener(itemExercise)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(listElements[position])
    }

    override fun getItemCount(): Int = listElements.size

}