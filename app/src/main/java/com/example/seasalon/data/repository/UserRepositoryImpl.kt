package com.example.seasalon.data.repository

import com.example.seasalon.data.local.UserDao
import com.example.seasalon.data.local.UserEntity
import com.example.seasalon.domain.model.User
import com.example.seasalon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun registerUser(userFullName: String, userEmail: String, userPhone: String, userPassword: String) {
        return userDao.registerUser(UserEntity(
            userFullName = userFullName,
            userPassword = userPassword,
            userEmail = userEmail,
            userPhone = userPhone
        ))
    }

    override suspend fun loginUser(email: String, password: String): User {
       return userDao.loginUSer(email, password).toDomain()
    }
}