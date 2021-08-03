package com.example.workoutapp.itemlistener

import android.view.View
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.rest.responsemodels.ParentElementResponse

interface OnItemClick<B> {
    fun onItemClickListener(item: ParentElementResponse, binding: B, view: View)
}