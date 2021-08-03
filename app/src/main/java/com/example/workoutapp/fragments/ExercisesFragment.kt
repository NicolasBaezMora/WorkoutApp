package com.example.workoutapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.adapters.CategoryExerciseAdapter
import com.example.workoutapp.adapters.ExerciseAdapter
import com.example.workoutapp.databinding.FragmentExercisesBinding
import com.example.workoutapp.databinding.ItemCategoryExerciseBinding
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responsemodels.CategoryElementResponse
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.responsemodels.ParentElementResponse
import com.example.workoutapp.viewmodels.ExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment : Fragment(), SearchView.OnQueryTextListener{

    private lateinit var exercisesFragBinding: FragmentExercisesBinding
    private lateinit var categoryAdapter: CategoryExerciseAdapter
    private lateinit var exerciseAdapter: ExerciseAdapter

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
        setupExercisesAdapter()
        exercisesFragBinding.searchViewElements.setOnQueryTextListener(this)

    }

    override fun onResume() {
        super.onResume()
        getDataCategories()
    }


    private fun setupExercisesAdapter() {
        exerciseAdapter = ExerciseAdapter(object : OnItemClick<ItemExerciseBinding> {
            override fun onItemClickListener(item: ParentElementResponse, binding: ItemExerciseBinding, view: View) {
                val itemData = item as ExerciseElementResponse
                navController.navigate(R.id.action_exercisesFragment_to_exerciseViewFragment, bundleOf(
                        "dataItem" to itemData
                ))
            }
        })
        exercisesFragBinding.recyclerViewExercises.adapter = exerciseAdapter
        exercisesFragBinding.recyclerViewExercises.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    private fun setupCategoryAdapter() {
        categoryAdapter = CategoryExerciseAdapter(object : OnItemClick<ItemCategoryExerciseBinding> {
            override fun onItemClickListener(item: ParentElementResponse, binding: ItemCategoryExerciseBinding, view: View) {
                val itemDataCategory = item as CategoryElementResponse
                vm.getResponseExercisesByCategory(itemDataCategory.id).observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val dataResponse = it.body()
                        if (dataResponse?.ok!!) {
                            exerciseAdapter.listElements = dataResponse.body
                            exerciseAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(requireContext(), requireContext().getString(R.string.occurredError), Toast.LENGTH_SHORT).show()
                    }
                })
            }

        })
        exercisesFragBinding.recyclerViewCategories.adapter = categoryAdapter
        exercisesFragBinding.recyclerViewCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getDataCategories() {
        vm.getResponseCategories().observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful){
                val dataResponse = it.body()
                if (dataResponse?.ok!!) {
                    categoryAdapter.listElements = dataResponse.body
                    categoryAdapter.notifyDataSetChanged()
                }
            } else {
                Toast.makeText(requireContext(), requireContext().getString(R.string.occurredError), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != "") {
            vm.getResponseExercisesByTitleFrag(query!!).observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    val dataResponse = it.body()
                    if (dataResponse?.ok!!) {
                        exerciseAdapter.listElements = dataResponse.body
                        exerciseAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), requireContext().getString(R.string.occurredError), Toast.LENGTH_SHORT).show()
                }
            })
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}