package com.example.seasalon.data.di

import android.content.Context
import androidx.room.Room
import com.example.seasalon.data.local.AppDatabase
import com.example.seasalon.data.local.ServicesDao
import com.example.seasalon.data.local.UserDao
import com.example.seasalon.data.repository.ServiceRepositoryImpl
import com.example.seasalon.data.repository.UserRepositoryImpl
import com.example.seasalon.domain.repository.ServiceRepository
import com.example.seasalon.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule{

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }


    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao{
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
      return UserRepositoryImpl(userDao = userDao)
    }

    @Provides
    fun provideServicesDao(appDatabase: AppDatabase) : ServicesDao{
        return appDatabase.serviceDao()
    }

    @Provides
    fun provideServicesRepository(servicesDao: ServicesDao): ServiceRepository {
        return ServiceRepositoryImpl(servicesDao = servicesDao)
    }

}