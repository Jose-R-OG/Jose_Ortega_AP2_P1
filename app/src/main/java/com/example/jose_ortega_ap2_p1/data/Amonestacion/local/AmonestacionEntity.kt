package com.example.jose_ortega_ap2_p1.data.Amonestacion.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amonestacion")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val AmonestacionId: Int = 0,
    val Nombres: String = "",
    val Razon: String = "",
    val Monto: Double = 0.0
)