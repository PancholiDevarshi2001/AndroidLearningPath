package com.base.hilt.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.LoginMutation
import com.base.hilt.UserDataQuery
import com.base.hilt.base.ViewModelBase
import com.base.hilt.domain.repository.UserRepository
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.LoginInput
import com.base.hilt.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val repo : LoginRepository):ViewModelBase() {

    val apicallLiveData = SingleLiveEvent<ResponseHandler<ApolloResponse<LoginMutation.Data>>?>()

    fun callLoginApiModel(input: LoginInput){
        viewModelScope.launch {
                apicallLiveData.postValue(ResponseHandler.Loading)
                val result = repo.callLoginApi(input)
                apicallLiveData.postValue(result)
        }
    }
}