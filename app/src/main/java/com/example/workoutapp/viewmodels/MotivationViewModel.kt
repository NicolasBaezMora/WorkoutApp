package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responsemodels.DefaultResponse
import com.example.workoutapp.rest.responsemodels.MotivationElementResponse
import com.example.workoutapp.rest.services.ApiRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class MotivationViewModel @ViewModelInject constructor(
        @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService
): ViewModel() {

    fun getResponseMotivation(): LiveData<Response<DefaultResponse<List<MotivationElementResponse>>>>{
        val responseMotivation = MutableLiveData<Response<DefaultResponse<List<MotivationElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getMotivations().execute()
            }
            responseMotivation.postValue(result)
        }
        return responseMotivation
    }

}