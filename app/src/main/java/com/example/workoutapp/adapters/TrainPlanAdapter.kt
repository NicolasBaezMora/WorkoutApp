package com.example.workoutapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemPlanExerciseBinding
import com.example.workoutapp.room.entities.ExercisePlan

class TrainPlanAdapter(
        val itemClickListener: MyOnClickListener
): RecyclerView.Adapter<TrainPlanAdapter.PlanViewHolder>() {

    companion object {
        var listPlans = emptyList<ExercisePlan>()
    }

    inner class PlanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val itemPlanBinding = ItemPlanExerciseBinding.bind(itemView)

        fun bind(itemPlan: ExercisePlan) {
            itemPlanBinding.textViewTitlePlan.text = itemPlan.title
            itemPlanBinding.btnPlayPlan.setOnClickListener{
                itemClickListener.onItemClickPlay(itemPlan)
            }
            itemPlanBinding.btnDeletePlan.setOnClickListener{
                itemClickListener.onItemClickDelete(itemPlan)
            }
        }
    }

    interface MyOnClickListener {
        fun onItemClickPlay(itemPlan: ExercisePlan)
        fun onItemClickDelete(itemPlan: ExercisePlan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan_exercise, parent, false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(listPlans[position])
    }

    override fun getItemCount(): Int = listPlans.size

}