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
import com.example.workoutapp.databinding.FragmentMakePlanBinding
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.itemlistener.OnItemClick
import com.example.workoutapp.models.TemporizedExerciseModel
import com.example.workoutapp.rest.responsemodels.ExerciseElementResponse
import com.example.workoutapp.rest.responsemodels.ParentElementResponse
import com.example.workoutapp.viewmodels.MakePlanViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MakePlanFragment : Fragment(), OnItemClick<ItemExerciseBinding>, View.OnClickListener {

    private lateinit var makePlanFragBinding: FragmentMakePlanBinding
    private lateinit var exerciseAdapter: ExerciseAdapter


    private val navController by lazy {findNavController()}
    private val vm by lazy { ViewModelProvider(this).get(MakePlanViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment.
        makePlanFragBinding = FragmentMakePlanBinding.inflate(layoutInflater, container, false)
        return makePlanFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makePlanFragBinding.btnExitTimer.setOnClickListener(this)
        setupExerciseAdapter()
        setupButtons()
    }

    override fun onResume() {
        super.onResume()
        getDataExercises()
    }


    private fun getDataExercises() {
        vm.getResponseExercise().observe(viewLifecycleOwner, Observer{
            if (it.isSuccessful){
                val dataResponse = it.body()
                if (dataResponse?.ok!!) {
                    exerciseAdapter.listElements = dataResponse.body
                    exerciseAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun setupExerciseAdapter() {
        exerciseAdapter = ExerciseAdapter(this)
        makePlanFragBinding.recyclerViewExercises.adapter = exerciseAdapter
        makePlanFragBinding.recyclerViewExercises.layoutManager = GridLayoutManager(context, 2)
    }

    private fun addExercise(itemExercise: ExerciseElementResponse, itemExerciseBinding: ItemExerciseBinding) {
        makePlanFragBinding.textViewTimerType.text = requireContext().getString(R.string.train_time_txt)
        vm.tempItemExercise = itemExercise
        vm.tempItemExerciseBinding = itemExerciseBinding
        makePlanFragBinding.viewTimer.visibility = View.VISIBLE
    }

    private fun removeExercise(itemExercise: ExerciseElementResponse, itemExerciseBinding: ItemExerciseBinding) {
        for (i in 0 until vm.listExercisesPlan.size) { if (vm.listExercisesPlan[i].idExercise == itemExercise.id) vm.listExercisesPlan.removeAt(i); break }
        itemExerciseBinding.textViewTrainTime.text = "00:00:00"
        itemExerciseBinding.textViewBreakTime.text = "00:00:00"
        itemExerciseBinding.cardViewItemExerciseDataTime.visibility = View.GONE
    }

    override fun onItemClickListener(item: ParentElementResponse, binding: ItemExerciseBinding, view: View) {
        when(view.id) {
            binding.cardViewItemExercise.id -> addExercise(item as ExerciseElementResponse, binding)
            binding.cardViewItemExerciseDataTime.id -> removeExercise(item as ExerciseElementResponse, binding)
        }
    }


    private fun setupButtons() {
        makePlanFragBinding.btn0.setOnClickListener(this)
        makePlanFragBinding.btn1.setOnClickListener(this)
        makePlanFragBinding.btn2.setOnClickListener(this)
        makePlanFragBinding.btn3.setOnClickListener(this)
        makePlanFragBinding.btn4.setOnClickListener(this)
        makePlanFragBinding.btn5.setOnClickListener(this)
        makePlanFragBinding.btn6.setOnClickListener(this)
        makePlanFragBinding.btn7.setOnClickListener(this)
        makePlanFragBinding.btn8.setOnClickListener(this)
        makePlanFragBinding.btn9.setOnClickListener(this)
        makePlanFragBinding.btnNext.setOnClickListener(this)
        makePlanFragBinding.btnErase.setOnClickListener(this)
        makePlanFragBinding.btnConfirmPlan.setOnClickListener(this)

    }

    private fun updateChronometerView() {
        when(vm.arrayNumbers.size) {
            0 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = "0"
                makePlanFragBinding.textViewM1.text = "0"
                makePlanFragBinding.textViewM2.text = "0"
                makePlanFragBinding.textViewS1.text = "0"
                makePlanFragBinding.textViewS2.text = "0"
            }
            1 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = "0"
                makePlanFragBinding.textViewM1.text = "0"
                makePlanFragBinding.textViewM2.text = "0"
                makePlanFragBinding.textViewS1.text = "0"
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[0].toString()
            }
            2 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = "0"
                makePlanFragBinding.textViewM1.text = "0"
                makePlanFragBinding.textViewM2.text = "0"
                makePlanFragBinding.textViewS1.text = vm.arrayNumbers[0].toString()
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[1].toString()
            }
            3 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = "0"
                makePlanFragBinding.textViewM1.text = "0"
                makePlanFragBinding.textViewM2.text = vm.arrayNumbers[0].toString()
                makePlanFragBinding.textViewS1.text = vm.arrayNumbers[1].toString()
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[2].toString()
            }
            4 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = "0"
                makePlanFragBinding.textViewM1.text = vm.arrayNumbers[0].toString()
                makePlanFragBinding.textViewM2.text = vm.arrayNumbers[1].toString()
                makePlanFragBinding.textViewS1.text = vm.arrayNumbers[2].toString()
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[3].toString()
            }
            5 -> {
                makePlanFragBinding.textViewH1.text = "0"
                makePlanFragBinding.textViewH2.text = vm.arrayNumbers[0].toString()
                makePlanFragBinding.textViewM1.text = vm.arrayNumbers[1].toString()
                makePlanFragBinding.textViewM2.text = vm.arrayNumbers[2].toString()
                makePlanFragBinding.textViewS1.text = vm.arrayNumbers[3].toString()
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[4].toString()
            }
            6 -> {
                makePlanFragBinding.textViewH1.text = vm.arrayNumbers[0].toString()
                makePlanFragBinding.textViewH2.text = vm.arrayNumbers[1].toString()
                makePlanFragBinding.textViewM1.text = vm.arrayNumbers[2].toString()
                makePlanFragBinding.textViewM2.text = vm.arrayNumbers[3].toString()
                makePlanFragBinding.textViewS1.text = vm.arrayNumbers[4].toString()
                makePlanFragBinding.textViewS2.text = vm.arrayNumbers[5].toString()
            }
        }
    }

    private fun clearTimer() {
        makePlanFragBinding.textViewH1.text = "0"
        makePlanFragBinding.textViewH2.text = "0"
        makePlanFragBinding.textViewM1.text = "0"
        makePlanFragBinding.textViewM2.text = "0"
        makePlanFragBinding.textViewS1.text = "0"
        makePlanFragBinding.textViewS2.text = "0"
        vm.arrayNumbers = mutableListOf()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            makePlanFragBinding.btnExitTimer.id -> {
                makePlanFragBinding.viewTimer.visibility = View.GONE
                clearTimer()
                vm.tempItemExerciseBinding = null
                vm.tempItemExercise = null
                vm.typeOfTimer = false
            }
            makePlanFragBinding.btn0.id -> if (vm.arrayNumbers.size in 1..5){
                vm.arrayNumbers.add(0)
                updateChronometerView()
            }
            makePlanFragBinding.btn1.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(1)
                updateChronometerView()
            }
            makePlanFragBinding.btn2.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(2)
                updateChronometerView()
            }
            makePlanFragBinding.btn3.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(3)
                updateChronometerView()
            }
            makePlanFragBinding.btn4.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(4)
                updateChronometerView()
            }
            makePlanFragBinding.btn5.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(5)
                updateChronometerView()
            }
            makePlanFragBinding.btn6.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(6)
                updateChronometerView()
            }
            makePlanFragBinding.btn7.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(7)
                updateChronometerView()
            }
            makePlanFragBinding.btn8.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(8)
                updateChronometerView()
            }
            makePlanFragBinding.btn9.id -> if (vm.arrayNumbers.size < 6) {
                vm.arrayNumbers.add(9)
                updateChronometerView()
            }
            makePlanFragBinding.btnNext.id -> if (!vm.typeOfTimer && vm.arrayNumbers.size > 0) {
                vm.tempTrainMillis = convertArrayToMillis()
                makePlanFragBinding.textViewTimerType.text =
                        requireContext().getString(R.string.break_time_txt)
                vm.typeOfTimer = true
                clearTimer()
            } else if (vm.typeOfTimer && vm.arrayNumbers.size > 0) {
                vm.tempBreakMillis = convertArrayToMillis()
                addExerciseToList()
                makePlanFragBinding.viewTimer.visibility = View.GONE
                vm.typeOfTimer = false
                clearTimer()
            } else {
                Toast.makeText(context, "No pueden haber tiempos en 0", Toast.LENGTH_SHORT).show()
            }
            makePlanFragBinding.btnErase.id -> if (vm.arrayNumbers.size > 0) {
                vm.arrayNumbers.removeLast()
                updateChronometerView()
            }
            makePlanFragBinding.btnConfirmPlan.id -> addNewPlan()
        }
    }

    private fun addNewPlan() {
        val titlePlan = makePlanFragBinding.editTextTitlePlan.text.toString()
        when {
            titlePlan.isEmpty() -> {
                Snackbar.make(requireView(), "Debes asignar un titulo a tu plan de ejercicios", Snackbar.LENGTH_SHORT).show()
                return
            }
            titlePlan.length > 20 -> {
                Snackbar.make(requireView(), "El titulo es demasiado largo", Snackbar.LENGTH_SHORT).show()
                return
            }
            vm.listExercisesPlan.isEmpty() -> {
                Snackbar.make(requireView(), "No has agregado ningun ejercicio a tu plan", Snackbar.LENGTH_SHORT).show()
                return
            }
            else -> vm.addPlan(titlePlan)
        }
    }

    private fun addExerciseToList() {
        vm.listExercisesPlan.add(TemporizedExerciseModel(
            vm.tempItemExercise?.id!!,
            vm.tempTrainMillis,
            vm.tempBreakMillis
        ))
        vm.tempItemExerciseBinding?.cardViewItemExerciseDataTime?.visibility = View.VISIBLE
        vm.tempItemExerciseBinding?.textViewTrainTime?.text = vm.tempTrainMillis.toString()
        vm.tempItemExerciseBinding?.textViewBreakTime?.text = vm.tempBreakMillis.toString()
        //--------------------------------
        vm.tempTrainMillis = 0
        vm.tempBreakMillis = 0
        //--------------------------------
        vm.tempItemExerciseBinding = null
        vm.tempItemExercise = null
    }

    private fun convertArrayToMillis(): Long {
        when(vm.arrayNumbers.size){
            1 -> {
                return TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[0]}".toLong())
            }
            2 -> {
                return TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[0]}${vm.arrayNumbers[1]}".toLong())
            }
            3 -> {
                return TimeUnit.MINUTES.toMillis("${vm.arrayNumbers[0]}".toLong()) +
                        TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[1]}${vm.arrayNumbers[2]}".toLong())
            }
            4 -> {
                return TimeUnit.MINUTES.toMillis("${vm.arrayNumbers[0]}${vm.arrayNumbers[1]}".toLong()) +
                        TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[2]}${vm.arrayNumbers[3]}".toLong())
            }
            5 -> {
                return TimeUnit.HOURS.toMillis("${vm.arrayNumbers[0]}".toLong()) +
                        TimeUnit.MINUTES.toMillis("${vm.arrayNumbers[1]}${vm.arrayNumbers[2]}".toLong()) +
                            TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[3]}${vm.arrayNumbers[4]}".toLong())
            }
            6 -> {
                return TimeUnit.HOURS.toMillis("${vm.arrayNumbers[0]}${vm.arrayNumbers[1]}".toLong()) +
                        TimeUnit.MINUTES.toMillis("${vm.arrayNumbers[2]}${vm.arrayNumbers[3]}".toLong()) +
                        TimeUnit.SECONDS.toMillis("${vm.arrayNumbers[4]}${vm.arrayNumbers[5]}".toLong())
            }
            else -> return 0L
        }
    }



}