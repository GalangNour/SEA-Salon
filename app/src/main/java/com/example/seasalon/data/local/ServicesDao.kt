package com.example.seasalon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ServicesDao {

    @Insert
    suspend fun insert(service: ServicesEntity)

    @Query("SELECT * FROM services_table")
    suspend fun getAll(): List<ServicesEntity>

    @Insert
    suspend fun insertAll(listOf: List<ServicesEntity>)


}