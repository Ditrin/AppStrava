package com.example.stravaver1.Oauth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stravaver1.R
import com.example.stravaver1.databinding.FragmentOauthBinding
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import com.example.stravaver1.utils.toast
import timber.log.Timber

class OauthFragment : Fragment(R.layout.fragment_oauth) {

    private val binding by viewBinding(FragmentOauthBinding::bind)
    private val viewModel: OauthViewModel by viewModels()
//    private val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
    private val sharedPref by lazy {
        requireContext().getSharedPreferences("token", Context.MODE_PRIVATE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == AUTH_REQUEST_CODE && data != null) {
            val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
                ?.createTokenExchangeRequest()
            val exception = AuthorizationException.fromIntent(data)
            when {
                tokenExchangeRequest != null && exception == null ->
                    viewModel.onAuthCodeReceived(tokenExchangeRequest)
                exception != null -> viewModel.onAuthCodeFailed(exception)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    object BdToken{
        var token : SharedPreferences? = null
    }

    private fun bindViewModel() {

        binding.oauthButton.setOnClickListener {viewModel.openLoginPage() }
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, ::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, ::toast)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            sharedPref
                .edit()
                .putString("token", OauthRepository.token.accessToken)
                .commit()
            BdToken.token = sharedPref
            Timber.d("value123 = ${sharedPref.getString("token", "no text")}")
            findNavController().navigate(R.id.action_OauthFragment_to_AthleteFragment)
        }
    }

    private fun updateIsLoading(isLoading: Boolean) = with(binding) {
       oauthButton.isVisible = !isLoading
    }

    private fun openAuthPage(intent: Intent) {

        startActivityForResult(intent, AUTH_REQUEST_CODE)
    }

    companion object {
        private const val AUTH_REQUEST_CODE = 342
    }
}