package com.example.jose_ortega_ap2_p1.data.Amonestacion.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(entity: AmonestacionEntity)
    @Delete
    suspend fun delete(entity: AmonestacionEntity)
    @Query(value = "Select * from amonestacion")
    fun observeAll(): Flow<List<AmonestacionEntity>>
    @Query(value = "Select * from amonestacion Where AmonestacionId =:id")
    suspend fun getById(id: Int): AmonestacionEntity?
    @Query(value = "Delete from amonestacion Where AmonestacionId =:id")
    suspend fun deleteById(id: Int)
    @Query(value = "select exists (select 1 from amonestacion Where AmonestacionId =:id)")
    suspend fun exists(id: Int): Boolean
}