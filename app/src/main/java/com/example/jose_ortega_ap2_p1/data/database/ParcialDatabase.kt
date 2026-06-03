package com.example.jose_ortega_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jose_ortega_ap2_p1.data.borrame.local.BorrameDao
import com.example.jose_ortega_ap2_p1.data.borrame.local.BorrameEntity

@Database(
    entities = [BorrameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ParcialDatabase : RoomDatabase() {
    abstract fun borrameDao(): BorrameDao
}