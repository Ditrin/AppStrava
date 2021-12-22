package com.example.stravaver1.networking.user.athlete

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.stravaver1.R
import com.example.stravaver1.databinding.FragmentAthleteBinding

class AthleteFragment : Fragment(R.layout.fragment_athlete){

    private val binding: FragmentAthleteBinding by viewBinding(FragmentAthleteBinding::bind)
    private val viewModel: AthleteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()

        viewModel.failToast.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.userInfo.observe(viewLifecycleOwner,{
            with(binding){
                firstname.text = it?.username
                countSubscribes.text = it?.countFriends.toString()
                countFollowers.text = it?.countFollowers.toString()
                photo.picture = it?.avatar
                Glide.with(view)
                    .load(it?.avatar)
                    .placeholder(R.drawable.no_face)
                    .into(avatarImage)
            }
        })
    binding.profile.setOnClickListener { findNavController().navigate(R.id.action_AthleteFragment_to_secondFragment) }
    }

    object photo {
        var picture: String? = null
    }
}