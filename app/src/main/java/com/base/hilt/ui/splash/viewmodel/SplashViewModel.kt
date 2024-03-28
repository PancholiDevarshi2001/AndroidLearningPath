package com.base.hilt.ui.splash.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.UserDataQuery
import com.base.hilt.base.ViewModelBase
import com.base.hilt.domain.repository.UserRepository
import com.base.hilt.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo :UserRepository):ViewModelBase() {

     val apicallLiveData = SingleLiveEvent<ApolloResponse<UserDataQuery.Data>>()

     fun callAPi(){
         viewModelScope.launch {
             try {
                 apicallLiveData.postValue(repo.callApi())
//                 apicallLiveData.value = repo.callApi()
             }catch (e:Exception){
                 Log.i("madmad", "callAPi: e")
             }
         }

    }


}