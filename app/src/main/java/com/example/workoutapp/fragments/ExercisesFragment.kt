package com.example.workoutapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.adapters.CategoryExerciseAdapter
import com.example.workoutapp.databinding.FragmentExercisesBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responses.CategoryElementResponse
import com.example.workoutapp.rest.responses.ParentElementResponse
import com.example.workoutapp.viewmodels.ExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment : Fragment(), OnItemClick {

    private lateinit var exercisesFragBinding: FragmentExercisesBinding
    private lateinit var categoryAdapter: CategoryExerciseAdapter

    private val navController by lazy { findNavController() }
    private val vm by lazy { ViewModelProvider(this).get(ExercisesViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        exercisesFragBinding = FragmentExercisesBinding.inflate(layoutInflater, container, false)
        return exercisesFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryAdapter()

    }

    override fun onResume() {
        super.onResume()
        getDataCategories()
    }

    private fun setupCategoryAdapter() {
        categoryAdapter = CategoryExerciseAdapter(this)
        exercisesFragBinding.recyclerViewCategories.adapter = categoryAdapter
        exercisesFragBinding.recyclerViewCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


    override fun onItemClickListener(item: ParentElementResponse) {
        Toast.makeText(context, "${(item as CategoryElementResponse).id}", Toast.LENGTH_SHORT).show()
    }

    private fun getDataCategories() {
        vm.getResponseCategories().observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful){
                val data = it.body()?.listCategories ?: emptyList()
                CategoryExerciseAdapter.listElements = data
                categoryAdapter.notifyDataSetChanged()
            } else {
                //NO INTERNET CONNECTION
            }
        })
    }

}