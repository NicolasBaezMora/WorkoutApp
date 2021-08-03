package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.room.dao.ExercisePlanDao
import com.example.workoutapp.room.dao.TemporizedExerciseDao
import com.example.workoutapp.room.entities.ExercisePlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Named

class TrainingPlansViewModel @ViewModelInject constructor(
        @Named(value = "exercisePlanDaoInstance") private val exercisePlanDaoInstance: ExercisePlanDao,
        @Named(value = "temporizedExerciseDaoInstance") private val temporizedExerciseDaoInstance: TemporizedExerciseDao
): ViewModel() {

    fun getPlans(): LiveData<List<ExercisePlan>> {
        val listPlans = MutableLiveData<List<ExercisePlan>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                exercisePlanDaoInstance.getPlans()
            }
            listPlans.postValue(result)
        }
        return listPlans
    }

    fun deletePlan(idPlan: Long): LiveData<Boolean> {
        val deletionFinished = MutableLiveData(false)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                temporizedExerciseDaoInstance.deleteExerciseByPlanId(idPlan)
                exercisePlanDaoInstance.deletePlanById(idPlan)
            }
            deletionFinished.postValue(true)
        }
        return deletionFinished
    }

}