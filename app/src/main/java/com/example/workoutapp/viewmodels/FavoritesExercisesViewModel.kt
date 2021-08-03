package com.example.workoutapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.rest.responsemodels.DefaultResponse
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.services.ApiRestService
import com.example.workoutapp.room.dao.FavoriteExerciseDao
import com.example.workoutapp.room.entities.FavoriteExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Named

class FavoritesExercisesViewModel @ViewModelInject constructor(
    @Named(value = "favoriteExerciseDaoInstance") private val favExerciseDaoInstance: FavoriteExerciseDao,
    @Named(value = "retrofitServiceInstance") private val retrofitServiceInstance: ApiRestService
): ViewModel() {

    private fun getFavoritesExercises(): LiveData<List<FavoriteExercise>> {
        val listFavExercises = MutableLiveData<List<FavoriteExercise>>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                favExerciseDaoInstance.getFavoritesExercises()
            }
            listFavExercises.postValue(result)
        }
        return listFavExercises
    }

    fun getExercisesByFav(): LiveData<List<ExerciseElementResponse>> {
        val listExercises = MutableLiveData<List<ExerciseElementResponse>>()
        val listExercisesConsulted = mutableListOf<ExerciseElementResponse>()
        getFavoritesExercises().observeForever {
            if (it.isNotEmpty()) {
                viewModelScope.launch {
                    for (i in it){
                        val result = withContext(Dispatchers.IO) {
                            retrofitServiceInstance.getExerciseById(i.valueIdExercise).execute()
                        }
                        if (result.isSuccessful) {
                            if (result.body()?.ok!!) listExercisesConsulted.add(result.body()!!.body)
                        }
                    }
                    listExercises.postValue(listExercisesConsulted)
                }
            }
        }
        return listExercises
    }

}













