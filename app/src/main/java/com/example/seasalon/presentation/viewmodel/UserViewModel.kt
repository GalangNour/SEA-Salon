package com.example.seasalon.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seasalon.domain.model.Resource
import com.example.seasalon.domain.model.User
import com.example.seasalon.domain.usecase.LoginUserUseCase
import com.example.seasalon.domain.usecase.RegisterUserUseCase
import com.example.seasalon.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State<User>>()
    val state : MutableLiveData<State<User>> = _state

    private val _userLoggedIn = MutableLiveData<Boolean>()
    val userLoggedIn: MutableLiveData<Boolean> = _userLoggedIn


    fun registerUser(userFullName: String, userEmail: String, userPassword: String, userPhone: String) {
        registerUserUseCase(userFullName, userEmail, userPhone, userPassword).launchIn(viewModelScope)
    }

    fun loginUser(email : String, password : String){
        loginUserUseCase(email, password).onEach {result->
            when(result){
                is Resource.Error -> {
                    Log.e("LoginUser", "Error: ${result.message}")
                    _state.value = State.Error(result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = State.Loading
                }
                is Resource.Success -> {
                    _state.value = result.data?.let { State.Success(it) }
                }
            }
        }.launchIn(viewModelScope)
    }


}