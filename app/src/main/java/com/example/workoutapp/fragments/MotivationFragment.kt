package com.example.workoutapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.adapters.MotivationAdapter
import com.example.workoutapp.databinding.FragmentMotivationBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.rest.responses.ParentElementResponse
import com.example.workoutapp.viewmodels.MotivationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MotivationFragment : Fragment(), OnItemClick {

    private lateinit var motivationFragBinding: FragmentMotivationBinding
    private lateinit var motivationAdapter: MotivationAdapter

    private val vm by lazy { ViewModelProvider(this).get(MotivationViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        motivationFragBinding = FragmentMotivationBinding.inflate(layoutInflater)
        return motivationFragBinding.root
        //return inflater.inflate(R.layout.fragment_motivation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMotivationAdapter()

    }

    override fun onResume() {
        super.onResume()
        getDataMotivations()
    }

    private fun setupMotivationAdapter() {
        motivationAdapter = MotivationAdapter(this)
        motivationFragBinding.recyclerViewMotivations.adapter = motivationAdapter
        motivationFragBinding.recyclerViewMotivations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun getDataMotivations() {
        vm.getResponseMotivation().observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful){
                val data = it.body()?.motivationList ?: emptyList()
                MotivationAdapter.listElements = data
                motivationAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClickListener(item: ParentElementResponse) {
        print("Hello world")
    }

}