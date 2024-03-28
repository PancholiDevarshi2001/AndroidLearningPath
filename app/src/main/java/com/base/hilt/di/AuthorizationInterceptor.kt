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
//        return if (MyApp.applicationContext().mPref.getValueString(PrefKey.TOKEN, "").isNullOrEmpty()) {
//            ""
//        } else {
//            "Bearer " + MyApp.applicationContext().mPref.getValueString(PrefKey.TOKEN, "")
//        }

        return  "Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiM2Y0MGJiZTFjZDA3NmJmNWM3MTQyMDFjMWE1NjdiZmI5ZTU4M2M5NDM3MTgxZDU0OWY1OTVkYmEzM2I3MTlkZTE0MDNmNTE0NzI2NGEyOGYiLCJpYXQiOjE3MTEwOTA1OTguNTIzNzE2LCJuYmYiOjE3MTEwOTA1OTguNTIzNzE3LCJleHAiOjE3NDI2MjY1OTguNDkyMzU2LCJzdWIiOiI2ODgiLCJzY29wZXMiOlsiKiJdfQ.ExyHXs-bf7ghvRnYU21mXFgwkr7PJu1rn9gD_aaM1XaPsRxnqoDbgF7lzNAtopSr-RaNBQ4XqlERsFk8mh22mEf4LUadlsvq04Wd9SJqhzHN4WAA3pZ0oYZ1eF27kzINWv9bfFvNhGp2TaWf_c0UrEFMxr31eHDbCV0x3_kXN37SUbaS_y8fX-ssQPENsQ-He0kW5SH2x7QMUsi89E3HFBKXT98hyn8UhW9tagQzDcZr1AkJw3OIA-53-fR6qw_nhIMM4e_eb_WGCws02viAaL-GGl7qyNPer59_g32yIlpBsm8e8UmFg1DT7n7imaR6S9C7uN29V0P71tmMNFGBII_1g5j5MGxW78_M7Pemov2l4_I0rTLGI-Hnk8QGYCDWUKNNHzE6SAmD_pBrc0Zh2GRWSaUKJy8In3bCn1aHP3YBxO6NXGfLe4N3OCj7ebwLiyBtANS9K-P8o-nKtcXFQOpKQO12aL-AL38tEevvqkOl5HOL3HW284hP-fCFmwtRhPrGLG6xTp4douVelrZcxTkiAWevOFdqp_s3xESuPnRigfLq3yes4K8LRdB3fLKMMo-Hi7lRL1bMSLzdcM7Udm1S_aN2czueNjg_s61LqVJd1tCWyPzCz82_tKMtUMPYJ-kn1iFBWWdsLqq038isZpWY7sSocmwmYihbzxsDf0Y"
    }
}
