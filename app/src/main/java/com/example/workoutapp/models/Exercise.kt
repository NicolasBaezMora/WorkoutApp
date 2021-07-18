package com.example.workoutapp.models

import java.io.Serializable

data class Exercise(
        val id: Int,
        val title: String,
        val imageUrl: String,
        val description: String,
        val category: CategoryExercise
): Serializable