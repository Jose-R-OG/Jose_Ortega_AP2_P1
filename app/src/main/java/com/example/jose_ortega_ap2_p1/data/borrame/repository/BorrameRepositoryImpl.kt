package com.example.jose_ortega_ap2_p1.data.borrame.repository

import com.example.jose_ortega_ap2_p1.data.borrame.local.BorrameDao
import com.example.jose_ortega_ap2_p1.domain.model.Borrame
import com.example.jose_ortega_ap2_p1.domain.repository.BorrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorrameRepositoryImpl @Inject constructor(private val localDataSource: BorrameDao):
    BorrameRepository {
    override fun observeBorrame(): Flow<List<Borrame>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBorrame(id: Int): Borrame? {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(borrame: Borrame): Int {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun exists(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}