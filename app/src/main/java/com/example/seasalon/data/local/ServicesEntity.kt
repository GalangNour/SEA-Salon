package com.example.seasalon.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seasalon.domain.model.Service

@Entity(tableName = "services_table")
data class ServicesEntity(
    @PrimaryKey(autoGenerate = true)    val id: Int = 0,
    @ColumnInfo(name = "services_name") val name: String,
    @ColumnInfo(name = "services_desc") val description: String,
    @ColumnInfo(name = "services_img")  val image: Int
){
    fun toDomain() : Service {
        return Service(
            id = id,
            name = name,
            description = description,
            image = image
        )
    }
}
