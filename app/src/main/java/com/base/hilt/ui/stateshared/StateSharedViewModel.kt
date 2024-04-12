package com.base.hilt.ui.stateshared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.hilt.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class StateSharedViewModel : ViewModelBase() {
    private val _livedata = MutableLiveData("Hello World")
    val liveData : LiveData<String> = _livedata
    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow = _stateFlow.asStateFlow()
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _stateFlow.asSharedFlow()

    fun triggerLiveData(){
        _livedata.value ="LiveData"
    }
    fun stateFlowLiveData(){
        _stateFlow.value ="State Flow"
    }
    fun triggerflow(): Flow<String> {
        return  flow {
            repeat(5){
                emit("Item $it")
                delay(1000)
            }
        }
    }
    fun triggerSharedFlow(){
        viewModelScope.launch {
            _sharedFlow.emit("sharedFlow")
        }
    }
}