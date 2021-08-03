package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.models.TemporizedExerciseModel
import com.example.workoutapp.rest.responsemodels.DefaultResponse
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.services.ApiRestService
import com.example.workoutapp.room.dao.ExercisePlanDao
import com.example.workoutapp.room.dao.TemporizedExerciseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Named

class MakePlanViewModel @ViewModelInject constructor(
        @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService,
        @Named(value = "exercisePlanDaoInstance") private val exercisePlanDaoInstance: ExercisePlanDao,
        @Named(value = "temporizedExerciseDaoInstance") private val temporizedExerciseDaoInstance: TemporizedExerciseDao
): ViewModel() {

    var tempTrainMillis: Long = 0
    var tempBreakMillis: Long = 0
    val listExercisesPlan = mutableListOf<TemporizedExerciseModel>()
    var arrayNumbers = mutableListOf<Int>()
    var typeOfTimer: Boolean = false
    var tempItemExercise: ExerciseElementResponse? = null
    var tempItemExerciseBinding: ItemExerciseBinding? = null

    fun getResponseExercise(): LiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>> {
        val responseExercises = MutableLiveData<Response<DefaultResponse<List<ExerciseElementResponse>>>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                retrofitServiceInstance.getExercises().execute()
            }
            responseExercises.postValue(result)
        }
        return responseExercises
    }

    fun addPlan(titlePlan: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                exercisePlanDaoInstance.addPlan(titlePlan)
            }
            for (i in 0 until listExercisesPlan.size) { listExercisesPlan[i].idPlan = result }
            for (i in listExercisesPlan) {
                withContext(Dispatchers.IO) {
                    temporizedExerciseDaoInstance.addTemporizedExercise(
                            i.idExercise,
                            i.trainTime,
                            i.breakTime,
                            i.idPlan!!
                    )
                }
            }
        }
    }


}