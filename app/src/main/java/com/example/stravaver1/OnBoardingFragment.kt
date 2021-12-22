package com.example.stravaver1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.stravaver1.Oauth.OauthRepository
import timber.log.Timber

class OnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val sharedPref by lazy {
        requireContext().getSharedPreferences("token", Context.MODE_PRIVATE)
    }

    private var startButton: Button? = null

       override fun onActivityCreated(savedInstanceState: Bundle?) {
           super.onActivityCreated(savedInstanceState)


        val value = sharedPref?.getString("token", "no text")
        Timber.d("value = ${value}")

            if (value != "no text")
                findNavController().navigate(R.id.action_onBoardingFragment_to_AthleteFragment)
            startButton = view?.findViewById(R.id.startButton)
            startButton?.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragment_to_OauthFragment)
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        startButton = null
    }
}