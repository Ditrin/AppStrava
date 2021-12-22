package com.example.stravaver1.networking

import com.example.stravaver1.Oauth.OauthPreference
import okhttp3.Interceptor
import okhttp3.Response

class AddToken : Interceptor {

//    val sharePref =

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = OauthPreference.token
        println(token)
        val originalRequest = chain.request()

        val request = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}