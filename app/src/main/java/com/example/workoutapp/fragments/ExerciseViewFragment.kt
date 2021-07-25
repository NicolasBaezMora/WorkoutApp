package com.example.workoutapp.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentExerciseViewBinding
import com.example.workoutapp.rest.responses.ExerciseElementResponse
import com.example.workoutapp.viewmodels.ViewExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseViewFragment : Fragment(), View.OnClickListener {

    private lateinit var exerciseFragBinding: FragmentExerciseViewBinding
    private lateinit var dataItemExercise: ExerciseElementResponse

    private var isFav: Boolean? =null
    private var isShowDescription = false

    private val vm by lazy { ViewModelProvider(this).get(ViewExerciseViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        exerciseFragBinding = FragmentExerciseViewBinding.inflate(layoutInflater, container, false)
        return exerciseFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataItemExercise = arguments?.getSerializable("dataItem") as ExerciseElementResponse
        setupLayoutExerciseView()
        setupFavIcon()

        exerciseFragBinding.btnViewDescription.setOnClickListener(this)
        exerciseFragBinding.imageViewBtnFavorite.setOnClickListener(this)

    }

    private fun setupFavIcon() {
        vm.verifyWasAdded(dataItemExercise.id).observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                isFav = true
                exerciseFragBinding.imageViewBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                isFav = false
                exerciseFragBinding.imageViewBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        })
    }

    private fun setupLayoutExerciseView() {
        exerciseFragBinding.textViewTitle.text = dataItemExercise.title
        exerciseFragBinding.textViewDescription.text = dataItemExercise.descriptionExercise
    }

    override fun onClick(view: View?){
        when(view?.id){
            exerciseFragBinding.btnViewDescription.id -> changeDescriptionVisibility()
            exerciseFragBinding.imageViewBtnFavorite.id -> addFavorite()

        }
    }

    private fun addFavorite() {
        isFav = if (isFav!!) {
            vm.removeFromFav(dataItemExercise.id)
            doAnimationFavIcon(isFav!!)
            false
        } else {
            vm.addToFav(dataItemExercise.id)
            doAnimationFavIcon(isFav!!)
            true
        }
    }

    private fun doAnimationFavIcon(isFav: Boolean) {
        val imgView = exerciseFragBinding.imageViewBtnFavorite
        if (isFav) {
            imgView.animate()
                .alpha(0f)
                .scaleX(.5f)
                .scaleY(.5f)
                .setDuration(250)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        imgView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        imgView.alpha = 1f
                        imgView.scaleX = 1f
                        imgView.scaleY = 1f
                    }
                })
        } else {
            Toast.makeText(context, "FUNCTION", Toast.LENGTH_SHORT).show()
            imgView.animate()
                .alpha(0.5f)
                .scaleX(.5f)
                .scaleY(.5f)
                .setDuration(250)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        imgView.setImageResource(R.drawable.ic_baseline_favorite_24)
                        imgView.alpha = 1f
                        imgView.scaleX = 1f
                        imgView.scaleY = 1f
                    }
                })
        }
    }

    private fun changeDescriptionVisibility() {
        if (!isShowDescription){
            exerciseFragBinding.textViewDescription.visibility = View.VISIBLE
            isShowDescription = true
        } else {
            exerciseFragBinding.textViewDescription.visibility = View.GONE
            isShowDescription = false
        }
    }

}