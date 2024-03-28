package com.base.hilt.di

import android.util.Log
import com.base.hilt.MyApp
import com.base.hilt.network.HttpCommonMethod
import com.base.hilt.utils.PrefKey
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = getAuthToken()
        Log.i("restapi", "intercept: ${getAuthToken()} ")
        if (token.isNotEmpty()) {
            request.addHeader("Authorization", getAuthToken())
        }
        return chain.proceed(request.build())
    }
    fun getAuthToken(): String {
        return if (MyApp.applicationContext().mPref.getValueString(PrefKey.TOKEN, "").isNullOrEmpty()) {
            ""
        } else {
            "Bearer " + MyApp.applicationContext().mPref.getValueString(PrefKey.TOKEN, "")
        }
    }
}
