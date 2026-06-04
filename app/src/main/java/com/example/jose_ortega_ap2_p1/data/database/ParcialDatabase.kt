package com.example.jose_ortega_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jose_ortega_ap2_p1.data.borrame.local.BorrameDao
import com.example.jose_ortega_ap2_p1.data.borrame.local.BorrameEntity

@Database(
    entities = [BorrameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ParcialDatabase : RoomDatabase() {
    abstract fun borrameDao(): BorrameDao
}