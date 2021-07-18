package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.adapters.ExerciseAdapter
import com.example.workoutapp.databinding.FragmentExerciseHomeBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responses.ExerciseElementResponse
import com.example.workoutapp.rest.responses.ParentElementResponse
import com.example.workoutapp.viewmodels.ExerciseHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseHomeFragment : Fragment(), View.OnClickListener, OnItemClick {

    private lateinit var exerciseHomeFragBinding: FragmentExerciseHomeBinding
    private lateinit var exerciseAdapter: ExerciseAdapter

    private val vm by lazy { ViewModelProvider(this).get(ExerciseHomeViewModel::class.java) }
    private val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        exerciseHomeFragBinding = FragmentExerciseHomeBinding.inflate(layoutInflater, container, false)
        return exerciseHomeFragBinding.root
        //return inflater.inflate(R.layout.fragment_exercise_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseHomeFragBinding.cardViewBtnSearch.setOnClickListener(this)
        setupCategoryAdapter()

    }

    override fun onResume() {
        super.onResume()
        getDataExercises()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            exerciseHomeFragBinding.cardViewBtnSearch.id -> navController.navigate(R.id.action_exerciseHomeFragment_to_exercisesFragment)
        }
    }

    private fun setupCategoryAdapter() {
        exerciseAdapter = ExerciseAdapter(this)
        exerciseHomeFragBinding.recyclerViewMyExercises.adapter = exerciseAdapter
        exerciseHomeFragBinding.recyclerViewMyExercises.layoutManager = GridLayoutManager(context, 2)
    }

    private fun getDataExercises() {
        vm.getResponseExercise().observe(viewLifecycleOwner, Observer{
            if (it.isSuccessful){
                val data = it.body()?.listExercises ?: emptyList()
                ExerciseAdapter.listElements = data
                exerciseAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClickListener(item: ParentElementResponse) {
        Toast.makeText(context, (item as ExerciseElementResponse).title, Toast.LENGTH_SHORT).show()
    }

}