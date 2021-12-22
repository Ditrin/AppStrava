package com.example.stravaver1.Oauth

import android.net.Uri
import net.openid.appauth.*
import android.content.SharedPreferences

class OauthRepository{


    var accessToken : String? = null
    fun getAuthRequest(): AuthorizationRequest {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse(OauthPreference.AUTH_URI),
            Uri.parse(OauthPreference.TOKEN_URI)

        )

        val redirectUri = Uri.parse(OauthPreference.REDIRECT_URI)

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            OauthPreference.CLIENT_ID,
            OauthPreference.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(OauthPreference.SCOPE)
            .build()
    }

    object token{
        var accessToken : String? = null
    }

    fun performTokenRequest(
        authService : AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) {

        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            when {
                response != null -> {
                    token.accessToken = response.accessToken.orEmpty()
                    OauthPreference.token = response.accessToken.orEmpty()
                    onComplete()
                }
                else -> onError()
            }
        }
    }

    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(OauthPreference.CLIENT_SECRET)
    }
}
