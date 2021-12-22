package com.example.stravaver1.Oauth

import net.openid.appauth.ResponseTypeValues

object OauthPreference {
    const val AUTH_URI = "https://www.strava.com/oauth/authorize"
    const val TOKEN_URI = "https://www.strava.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "profile:read_all,activity:read_all"

    const val CLIENT_ID = "72477"
    const val CLIENT_SECRET = "dbf3bcd3add81e15f92e55814f5d5d2621319ed3"
    const val REDIRECT_URI = "strava://strava.auth/callback"
    var token : String? = null
}