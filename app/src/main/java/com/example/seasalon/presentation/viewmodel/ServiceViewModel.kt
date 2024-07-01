package com.example.seasalon.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seasalon.domain.model.Resource
import com.example.seasalon.domain.model.Service
import com.example.seasalon.domain.usecase.GetAllServicesUseCase
import com.example.seasalon.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getAllServicesUseCase: GetAllServicesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State<List<Service>>>()
    val state : MutableLiveData<State<List<Service>>> = _state

    fun getAllServices() {
        getAllServicesUseCase.invoke().onEach {result->
            when (result){
                is Resource.Error -> {
                    Log.e("Get All Services", "Error: ${result.message}")
                    _state.value = State.Error(result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = State.Loading
                }
                is Resource.Success -> {
                    _state.value = State.Success(result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}