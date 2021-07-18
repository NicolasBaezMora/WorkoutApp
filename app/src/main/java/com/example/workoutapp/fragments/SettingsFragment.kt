package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsFragBinding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        settingsFragBinding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return settingsFragBinding.root
    }
}