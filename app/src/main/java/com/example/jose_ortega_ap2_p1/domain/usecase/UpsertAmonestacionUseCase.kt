package com.example.jose_ortega_ap2_p1.domain.usecase

import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion
import com.example.jose_ortega_ap2_p1.domain.repository.AmonestacionRepository
import com.example.registrodeocupaciones.domain.empleado.usecase.validarNombres
import com.example.registrodeocupaciones.domain.empleado.usecase.validarRazon
import com.example.registrodeocupaciones.domain.empleado.usecase.validarmonto
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int>{
        val nombresResult = validarNombres(amonestacion.nombres)

        if(!nombresResult.isValid){
            return Result.failure(exception = IllegalArgumentException(nombresResult.error))
        }

        val razonResult = validarRazon(amonestacion.razon)
        if(!razonResult.isValid){
            return Result.failure(exception = IllegalArgumentException(razonResult.error))
        }

        val montoResult = validarmonto(amonestacion.monto.toString())
        if(!montoResult.isValid){
            return Result.failure(exception = IllegalArgumentException(montoResult.error))
        }
        return runCatching { repository.upsert(amonestacion) }
    }
}