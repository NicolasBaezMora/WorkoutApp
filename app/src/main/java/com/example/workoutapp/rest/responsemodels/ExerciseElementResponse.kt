package com.example.workoutapp.rest.responsemodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExerciseElementResponse(
    @SerializedName(value = "id") val id: Long,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "imageUrl") val imageUrl: String,
    @SerializedName(value = "description") val descriptionExercise: String,
    @SerializedName(value = "category") val category: CategoryElementResponse
): ParentElementResponse(), Serializable
