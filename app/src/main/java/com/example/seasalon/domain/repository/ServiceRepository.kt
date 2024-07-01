package com.example.seasalon.domain.repository

import com.example.seasalon.domain.model.Service

interface ServiceRepository {

    suspend fun getAllServices(): List<Service>

    suspend fun insertService(service: Service)
}