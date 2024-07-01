package com.example.seasalon.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seasalon.domain.model.User

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey (autoGenerate = true)       val userId: Int = 0,
    @ColumnInfo(name = "user_full_name")    val userFullName : String,
    @ColumnInfo (name = "user_email")       val userEmail : String,
    @ColumnInfo (name = "user_phone")       val userPhone : String,
    @ColumnInfo (name = "user_password")    val userPassword : String,
    @ColumnInfo (name = "isAdmin")          val isAdmin : Boolean = false

) {
    fun toDomain(): User {
        return User(
            userId = userId,
            userFullName = userFullName,
            userEmail = userEmail,
            userPhone = userPhone,
            userPassword = userPassword,
        )
    }
}