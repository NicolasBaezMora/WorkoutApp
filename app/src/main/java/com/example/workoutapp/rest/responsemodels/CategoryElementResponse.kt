package com.example.workoutapp.rest.responsemodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryElementResponse(
    @SerializedName(value = "id") val id: Long,
    @SerializedName(value = "title") val title: String
): ParentElementResponse(), Serializable