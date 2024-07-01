package com.example.seasalon.domain.usecase

import com.example.seasalon.domain.model.Resource
import com.example.seasalon.domain.model.Service
import com.example.seasalon.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllServicesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    operator fun invoke() : Flow<Resource<List<Service>>> = flow {
        emit(Resource.Loading())
        try {
            val services = serviceRepository.getAllServices()
            emit(Resource.Success(services))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

}