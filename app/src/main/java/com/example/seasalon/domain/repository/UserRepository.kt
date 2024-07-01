package com.example.seasalon.domain.repository

import com.example.seasalon.data.local.UserEntity
import com.example.seasalon.domain.model.User

interface UserRepository {

    suspend fun registerUser(userFullName : String, userEmail : String, userPhone : String, userPassword : String)

    suspend fun loginUser(email : String, password : String) : User


}