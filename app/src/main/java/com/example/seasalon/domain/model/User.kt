package com.example.seasalon.domain.model

import com.example.seasalon.data.local.UserEntity

data class User(
    val userId: Int = 0,
    val userFullName : String,
    val userEmail : String,
    val userPhone : String,
    val userPassword : String,
    val isAdmin : Boolean = false
)


