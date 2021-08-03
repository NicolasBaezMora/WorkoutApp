package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responsemodels.CategoryElementResponse
import com.example.workoutapp.rest.responsemodels.DefaultResponse
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.services.ApiRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class ExercisesViewModel @ViewModelInject constructor(
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


    fun getResponseCategories(): LiveData<Response<DefaultResponse<List<CategoryElementResponse>>>> {
        val responseCategories = MutableLiveData<Response<DefaultResponse<List<CategoryElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getCategories().execute()
            }
            responseCategories.postValue(result)
        }
        return responseCategories
    }

    fun getResponseExercisesByCategory(idCategory: Long): LiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>> {
        val responseExercises = MutableLiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getExercisesByCategory(idCategory).execute()
            }
            responseExercises.postValue(result)
        }
        return responseExercises
    }

    fun getResponseExercisesByTitleFrag(fragTitle: String): LiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>> {
        val responseExercises = MutableLiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getExercisesByFragTitle(fragTitle).execute()
            }
            responseExercises.postValue(result)
        }
        return responseExercises
    }

}