package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.room.dao.FavoriteExerciseDao
import com.example.workoutapp.room.entities.FavoriteExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Named

class ViewExerciseViewModel @ViewModelInject constructor(
    @Named(value = "favoriteExerciseDaoInstance") private val favExerciseDaoInstance: FavoriteExerciseDao
): ViewModel() {

    fun addToFav(idExercise: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favExerciseDaoInstance.addExercise(idExercise)
            }
        }
    }

    fun removeFromFav(idExercise: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favExerciseDaoInstance.deleteFavoriteExercise(idExercise)
            }
        }
    }

    fun verifyWasAdded(idExercise: Long): LiveData<List<FavoriteExercise>>{
        val exerciseAdded = MutableLiveData<List<FavoriteExercise>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                favExerciseDaoInstance.getFavoriteExerciseById(idExercise)
            }
            exerciseAdded.postValue(result)
        }
        return exerciseAdded
    }

}





















