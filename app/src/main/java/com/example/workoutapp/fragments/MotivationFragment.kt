package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentMotivationBinding

class MotivationFragment : Fragment() {

    private lateinit var motivationFragBinding: FragmentMotivationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        motivationFragBinding = FragmentMotivationBinding.inflate(layoutInflater)
        return motivationFragBinding.root
        //return inflater.inflate(R.layout.fragment_motivation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }

}