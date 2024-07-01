package com.example.seasalon.domain.model

data class Branch(
    val id: Int,
    val name: String,
    val services: List<Service>
)
