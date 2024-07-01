package com.example.seasalon.data.repository

import com.example.seasalon.data.local.ServicesDao
import com.example.seasalon.data.local.ServicesEntity
import com.example.seasalon.domain.model.Service
import com.example.seasalon.domain.repository.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val servicesDao: ServicesDao
) : ServiceRepository {

    override suspend fun getAllServices(): List<Service> {
        return servicesDao.getAll().map { it.toDomain() }
    }

    override suspend fun insertService(service: Service) {
        return servicesDao.insert(ServicesEntity(
            name = service.name,
            description = service.description,
            image = service.image
        ))
    }

}