package com.example.seasalon.domain.usecase

import com.example.seasalon.domain.model.Resource
import com.example.seasalon.domain.model.User
import com.example.seasalon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userFullName: String, userEmail: String, userPhone: String, userPassword: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            userRepository.registerUser(userFullName, userEmail, userPhone, userPassword)
            emit(Resource.Success(User(
                userFullName = userFullName,
                userEmail = userEmail,
                userPassword = userPassword,
                userPhone = userPhone
            )))
        } catch (e: Exception) {
            emit(Resource.Error("Error Occurred"))
        }
    }
}