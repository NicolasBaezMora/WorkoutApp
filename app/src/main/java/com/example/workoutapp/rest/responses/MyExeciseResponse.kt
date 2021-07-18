package com.example.workoutapp.rest.responses

import com.google.gson.annotations.SerializedName

data class ExercisesResponse(
    @SerializedName(value = "exerciseList") val listExercises: List<ExerciseElementResponse>
)

data class ExerciseElementResponse(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "imageUrl") val imageUrl: String,
    @SerializedName(value = "description") val descriptionExercise: String,
    @SerializedName(value = "category") val category: CategoryElementResponse
): ParentElementResponse()