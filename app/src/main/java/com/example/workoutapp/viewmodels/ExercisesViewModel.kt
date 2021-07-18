package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responses.CategoriesResponse
import com.example.workoutapp.rest.services.ApiRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class ExercisesViewModel @ViewModelInject constructor(
        @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService
): ViewModel() {

    fun getResponseCategories(): LiveData<Response<CategoriesResponse>> {
        val responseCategories = MutableLiveData<Response<CategoriesResponse>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getCategories().execute()
            }
            responseCategories.postValue(result)
        }
        return responseCategories
    }

}