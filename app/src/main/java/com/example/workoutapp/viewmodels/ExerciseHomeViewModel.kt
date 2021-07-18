package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responses.ExercisesResponse
import com.example.workoutapp.rest.services.ApiRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class ExerciseHomeViewModel @ViewModelInject constructor(
    @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService
): ViewModel() {

    fun getResponseExercise(): LiveData<Response<ExercisesResponse>>{
        val responseExercises = MutableLiveData<Response<ExercisesResponse>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getExercises().execute()
            }
            responseExercises.postValue(result)
        }
        return responseExercises
    }


}