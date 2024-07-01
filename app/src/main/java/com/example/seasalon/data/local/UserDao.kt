package com.example.seasalon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(vararg user: UserEntity)

    @Query("SELECT * FROM user_table WHERE user_email LIKE :email AND user_password LIKE :password")
    suspend fun loginUSer(email: String, password: String): UserEntity
}