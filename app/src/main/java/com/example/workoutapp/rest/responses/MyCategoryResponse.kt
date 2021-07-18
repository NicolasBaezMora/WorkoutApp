package com.example.workoutapp.rest.responses

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName(value = "categoryList") val listCategories: List<CategoryElementResponse>
)

data class CategoryElementResponse(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "title") val title: String
): ParentElementResponse()