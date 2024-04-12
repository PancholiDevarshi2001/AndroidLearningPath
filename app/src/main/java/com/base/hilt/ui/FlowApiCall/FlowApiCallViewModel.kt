package com.base.hilt.ui.FlowApiCall

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.base.hilt.base.ViewModelBase
import com.base.hilt.network.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowApiCallViewModel @Inject constructor(private val repo: FlowApiCallRepository) : ViewModelBase(){


    private val _uiState = MutableStateFlow<UiState<List<ApiUserModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiUserModel>>> = _uiState

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch{
            _uiState.value = UiState.Loading
            repo.getUsers()
//                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it!!)
                }
        }
    }

}