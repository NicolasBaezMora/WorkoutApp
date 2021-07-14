package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentTrainingPlansBinding

class TrainingPlansFragment : Fragment() {

    private lateinit var trainingPlansFragBinding: FragmentTrainingPlansBinding

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

    }
}