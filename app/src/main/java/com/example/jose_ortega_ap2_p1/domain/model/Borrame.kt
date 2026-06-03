package com.example.jose_ortega_ap2_p1.domain.model

import java.time.LocalDate

data class Borrame(
    val borrameId: Int = 0,
    val fecha: LocalDate = LocalDate.now(),
    val descripcion: String = ""
)