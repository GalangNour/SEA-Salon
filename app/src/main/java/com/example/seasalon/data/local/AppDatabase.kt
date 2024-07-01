package com.example.seasalon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.seasalon.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserEntity::class, ServicesEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun serviceDao(): ServicesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Pre-populate the database on first creation
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        prepopulateDatabase(database.userDao(), database.serviceDao())
                    }
                }
            }
        }

        private suspend fun prepopulateDatabase(userDao: UserDao, serviceDao: ServicesDao) {
            // Insert initial data
            userDao.registerUser(
                UserEntity(
                    userFullName = "Thomas N",
                    userEmail = "thomas.n@compfest.id",
                    userPhone = "08123456789",
                    userPassword = "Admin123",
                    isAdmin = true
                )
            )

            serviceDao.insert(
                ServicesEntity(
                    name = "Haircuts and Styling",
                    description = "Haircuts and Styling",
                    image = R.drawable.haircut_styling // Make sure this is the correct drawable resource name
                )
            )

            serviceDao.insert(
                ServicesEntity(
                    name = "Manicure and Pedicure",
                    description = "Manicure and Pedicure",
                    image = R.drawable.manicure_pedicure // Make sure this is the correct drawable resource name
                )
            )

            serviceDao.insert(
                ServicesEntity(
                    name = "Facial Treatment",
                    description = "Facial Treatment",
                    image = R.drawable.facial_treatment // Make sure this is the correct drawable resource name
                )
            )
        }
    }
}
