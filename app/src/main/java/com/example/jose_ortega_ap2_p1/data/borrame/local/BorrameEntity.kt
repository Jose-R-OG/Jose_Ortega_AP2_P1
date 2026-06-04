package com.example.jose_ortega_ap2_p1.data.borrame.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "borrame")
data class BorrameEntity(
    @PrimaryKey(autoGenerate = true)
    val borrameId: Int = 0,
    val fecha: LocalDate = LocalDate.now(),
    val descripcion: String = ""
)