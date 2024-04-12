package com.base.hilt.network

import com.base.hilt.ui.FlowApiCall.ApiUserModel
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {
    @GET("https://5e510330f2c0d300147c034c.mockapi.io/users")
    suspend fun getUsers(): Response<List<ApiUserModel>>

}
