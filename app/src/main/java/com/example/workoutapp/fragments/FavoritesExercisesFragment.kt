package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.adapters.ExerciseAdapter
import com.example.workoutapp.databinding.FragmentFavoritesExercisesBinding
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.responsemodels.ParentElementResponse
import com.example.workoutapp.viewmodels.FavoritesExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesExercisesFragment : Fragment(), OnItemClick<ItemExerciseBinding> {

    private lateinit var favoritesExercisesFragBinding: FragmentFavoritesExercisesBinding
    private lateinit var exerciseAdapter: ExerciseAdapter

    private val vm by lazy { ViewModelProvider(this).get(FavoritesExercisesViewModel::class.java)}
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        favoritesExercisesFragBinding = FragmentFavoritesExercisesBinding.inflate(layoutInflater, container, false)
        return favoritesExercisesFragBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupExerciseAdapter()

    }

    private fun setupExerciseAdapter() {
        exerciseAdapter = ExerciseAdapter(this)
        favoritesExercisesFragBinding.recyclerViewFavoritesExercises.adapter = exerciseAdapter
        favoritesExercisesFragBinding.recyclerViewFavoritesExercises.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onResume() {
        super.onResume()
        getDataExercises()
    }

    private fun getDataExercises() {
        vm.getExercisesByFav().observe(viewLifecycleOwner, Observer {
            exerciseAdapter.listElements = it
            exerciseAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClickListener(item: ParentElementResponse, binding: ItemExerciseBinding, view: View) {
        val itemExerciseElement: ExerciseElementResponse = item as ExerciseElementResponse
        navController.navigate(R.id.action_favoritesExercisesFragment_to_exerciseViewFragment, bundleOf(
            "dataItem" to itemExerciseElement
        ))
    }

}
















