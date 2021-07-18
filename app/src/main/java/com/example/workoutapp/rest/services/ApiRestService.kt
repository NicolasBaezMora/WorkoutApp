package com.example.workoutapp.rest.services

import com.example.workoutapp.rest.responses.CategoriesResponse
import com.example.workoutapp.rest.responses.ExercisesResponse
import com.example.workoutapp.rest.responses.MotivationsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiRestService {

    @GET(value = "exercise/exercises")
    fun getExercises(): Call<ExercisesResponse>

    @GET(value = "category/categories")
    fun getCategories(): Call<CategoriesResponse>
    
    @GET(value = "motivation/motivations")
    fun getMotivations(): Call<MotivationsResponse>

}