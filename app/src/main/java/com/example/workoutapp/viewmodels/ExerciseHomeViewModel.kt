package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responsemodels.DefaultResponse
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.services.ApiRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class ExerciseHomeViewModel @ViewModelInject constructor(
    @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService
): ViewModel() {

    fun getResponseExercise(): LiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>>{
        val responseExercises = MutableLiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getExercises().execute()
            }
            responseExercises.postValue(result)
        }
        return responseExercises
    }


}