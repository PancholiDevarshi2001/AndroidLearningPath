package com.base.hilt.ui.createAccount

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.CreateAccountMutation
import com.base.hilt.VerifyOtpMutation
import com.base.hilt.base.ViewModelBase
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import com.base.hilt.ui.login.LoginRepository
import com.base.hilt.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(val repository: LoginRepository) :
    ViewModelBase() {

    private val _signUpLiveData = SingleLiveEvent<ApolloResponse<CreateAccountMutation.Data>>()
    val signUpLiveData: SingleLiveEvent<ApolloResponse<CreateAccountMutation.Data>> = _signUpLiveData

    val otpVerifyLiveData = SingleLiveEvent<ApolloResponse<VerifyOtpMutation.Data>>()

    fun signUpApi(signUpInput : SignUpInput){
        viewModelScope.launch {
            _signUpLiveData.postValue(repository.onCreateAccountApi(signUpInput))
           signUpLiveData.value = repository.onCreateAccountApi(SignUpInput())
        }
    }
    fun otpVerifyApi(input : OtpInput){
        viewModelScope.launch {
            otpVerifyLiveData.postValue(repository.verifyOtp(input))
            val result =  repository.verifyOtp(input)
            otpVerifyLiveData.value = result
        }
    }
}