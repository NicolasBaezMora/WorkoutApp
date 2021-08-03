package com.example.workoutapp.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.adapters.TrainPlanAdapter
import com.example.workoutapp.databinding.CustomAlertDialogDeletePlanBinding
import com.example.workoutapp.databinding.FragmentTrainingPlansBinding
import com.example.workoutapp.room.entities.ExercisePlan
import com.example.workoutapp.viewmodels.TrainingPlansViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingPlansFragment : Fragment(), View.OnClickListener, TrainPlanAdapter.MyOnClickListener {

    private lateinit var trainingPlansFragBinding: FragmentTrainingPlansBinding
    private lateinit var trainPlanAdapter: TrainPlanAdapter

    private val navController by lazy {findNavController()}
    private val vm by lazy { ViewModelProvider(this).get(TrainingPlansViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        trainingPlansFragBinding = FragmentTrainingPlansBinding.inflate(layoutInflater, container, false)
        return trainingPlansFragBinding.root
        //return inflater.inflate(R.layout.fragment_training_plans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trainingPlansFragBinding.floatBtnAddPlan.setOnClickListener(this)
        setupPlanAdapter()

    }

    override fun onResume() {
        super.onResume()
        getDataPlanExercise()
    }

    private fun setupPlanAdapter() {
        trainPlanAdapter = TrainPlanAdapter(this)
        trainingPlansFragBinding.recyclerViewPlans.adapter = trainPlanAdapter
        trainingPlansFragBinding.recyclerViewPlans.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun getDataPlanExercise() {
        vm.getPlans().observe(viewLifecycleOwner, Observer {
            TrainPlanAdapter.listPlans = it
            trainPlanAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            trainingPlansFragBinding.floatBtnAddPlan.id -> navController.navigate(R.id.action_trainingPlansFragment_to_makePlanFragment)
        }
    }

    override fun onItemClickPlay(itemPlan: ExercisePlan) {
        Toast.makeText(requireContext(), "Plan iniciado", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClickDelete(itemPlan: ExercisePlan) {
        val builderAlertDialog = AlertDialog.Builder(context)
        builderAlertDialog.setMessage("Estas se guro de eliminar '${itemPlan.title}' de tu plan de ejercicios?")
        builderAlertDialog.setPositiveButton ("si") { _ , _ ->
            vm.deletePlan(itemPlan.id).observe(viewLifecycleOwner, Observer {
                if (it) getDataPlanExercise()
            })
        }
        builderAlertDialog.setNegativeButton("cancelar") { _, _ ->}
        builderAlertDialog.create()
        builderAlertDialog.show()
    }
}











