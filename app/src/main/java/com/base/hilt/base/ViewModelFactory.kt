package com.base.hilt.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.base.hilt.network.ApiHelper
import com.base.hilt.network.DefaultDispatcherProvider
import com.base.hilt.ui.FlowApiCall.FlowApiCallViewModel
import javax.inject.Inject
import javax.inject.Provider
//
//class ViewModelFactory @Inject constructor(private val dispatcherProvider:DefaultDispatcherProvider) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FlowApiCallViewModel::class.java)) {
//            return FlowApiCallViewModel(dispatcherProvider) as T
//        }
//        throw IllegalArgumentException("Unknown class name")
//    }
//}