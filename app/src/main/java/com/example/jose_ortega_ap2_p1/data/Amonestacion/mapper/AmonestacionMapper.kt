package com.example.jose_ortega_ap2_p1.data.Amonestacion.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jose_ortega_ap2_p1.data.Amonestacion.local.AmonestacionEntity
import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion

fun AmonestacionEntity.toDomain() : Amonestacion = Amonestacion(
    amonestacionId = AmonestacionId,
    nombres = Nombres,
    razon = Razon,
    monto = Monto

)

@RequiresApi(Build.VERSION_CODES.O)
fun Amonestacion.toEntity(): AmonestacionEntity = AmonestacionEntity(
    AmonestacionId = amonestacionId,
    Nombres = nombres,
    Razon = razon,
    Monto = monto
)