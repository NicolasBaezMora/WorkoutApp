package com.example.workoutapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemCategoryExerciseBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responsemodels.CategoryElementResponse

class CategoryExerciseAdapter(
        private val onItemClick: OnItemClick<ItemCategoryExerciseBinding>
): RecyclerView.Adapter<CategoryExerciseAdapter.CategoriesExerciseViewHolder>() {

    var listElements = listOf<CategoryElementResponse>()


    inner class CategoriesExerciseViewHolder(itemViewCategoryExercise: View): RecyclerView.ViewHolder(itemViewCategoryExercise){

        private val exerciseItemBinding = ItemCategoryExerciseBinding.bind(itemViewCategoryExercise)

        fun bind(itemCategoryExercise: CategoryElementResponse){
            exerciseItemBinding.btnCategory.text = itemCategoryExercise.title
            exerciseItemBinding.btnCategory.setOnClickListener {
                onItemClick.onItemClickListener(itemCategoryExercise, exerciseItemBinding, it)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_exercise, parent, false)
        return CategoriesExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holderCategories: CategoriesExerciseViewHolder, position: Int) {
        holderCategories.bind(listElements[position])
    }

    override fun getItemCount(): Int = listElements.size

}