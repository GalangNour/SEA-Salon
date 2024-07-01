package com.example.seasalon.domain.usecase

import android.content.res.Resources
import com.example.seasalon.domain.model.Resource
import com.example.seasalon.domain.model.User
import com.example.seasalon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(email: String, password: String) : Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val user = userRepository.loginUser(email, password)
            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error("User not found"))
            }
        }catch (e: Exception){
            emit(Resource.Error("Error Occurred"))
        }
    }
}