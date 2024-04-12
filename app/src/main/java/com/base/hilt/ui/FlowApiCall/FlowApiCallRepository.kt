package com.base.hilt.ui.FlowApiCall

import android.util.Log
import com.base.hilt.base.BaseRepository
import com.base.hilt.network.ApiInterface
import com.base.hilt.network.ResponseData
import com.base.hilt.network.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class FlowApiCallRepository @Inject constructor (val apiInterface : ApiInterface ) : BaseRepository() {


    suspend fun getUsers() : Flow<List<ApiUserModel>?> {
        return flow {
            val response: Response<List<ApiUserModel>> = apiInterface.getUsers()
            emit(response.body())
        }
    }

}