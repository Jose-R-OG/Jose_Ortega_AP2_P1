package com.example.jose_ortega_ap2_p1.domain.repository

import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAmonestaciones(): Flow<List<Amonestacion>>
    suspend fun getAmonestacion(id: Int): Amonestacion?
    suspend fun upsert(Amonestacion: Amonestacion): Int
    suspend fun delete(id: Int)
    suspend fun exists(id: Int): Boolean
}