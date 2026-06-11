package com.example.jose_ortega_ap2_p1.data.Amonestacion.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jose_ortega_ap2_p1.data.Amonestacion.local.AmonestacionDao
import com.example.jose_ortega_ap2_p1.data.Amonestacion.mapper.toDomain
import com.example.jose_ortega_ap2_p1.data.Amonestacion.mapper.toEntity
import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion
import com.example.jose_ortega_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(private val localDataSource: AmonestacionDao):
     AmonestacionRepository{
        override fun observeAmonestaciones(): Flow<List<Amonestacion>> {
            return localDataSource.observeAll().map{entities ->
                entities.map {it.toDomain()}
            }
        }

        override suspend fun getAmonestacion(id: Int): Amonestacion?{
            return localDataSource.getById(id)?.toDomain()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override suspend fun upsert(amonestacion: Amonestacion): Int{
            localDataSource.upsert(entity = amonestacion.toEntity())
            return amonestacion.amonestacionId
        }

        override suspend fun delete(id: Int){
            localDataSource.deleteById(id)
        }

        override suspend fun exists(Id: Int): Boolean {
            return localDataSource.exists(Id)
        }

}