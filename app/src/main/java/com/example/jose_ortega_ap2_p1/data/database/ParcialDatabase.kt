package com.example.jose_ortega_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jose_ortega_ap2_p1.data.Amonestacion.local.AmonestacionDao
import com.example.jose_ortega_ap2_p1.data.Amonestacion.local.AmonestacionEntity

@Database(
    entities = [AmonestacionEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ParcialDatabase : RoomDatabase() {
    abstract fun AmonestacionDao(): AmonestacionDao
}