package com.example.registrodeocupaciones.domain.empleado.usecase


data class ValidationResult(
    val isValid: Boolean,
    val error: String?  = null
)

fun validarNombres(nombres: String): ValidationResult {
    return when{
        nombres.isBlank() -> ValidationResult(false, "El nombre es obligatorio")
        nombres.length < 2 -> ValidationResult(false, "Minimo 2 caracteres")
        else -> ValidationResult(true)
    }
}

fun validarmonto(monto: String): ValidationResult {
    return when{
        monto.toDouble() <= 0 -> ValidationResult(false, "El monto debe ser mayor a 0")
        else -> ValidationResult(true)
    }
}

fun validarRazon(razon: String): ValidationResult {
    return when{
        razon.isBlank() -> ValidationResult(false, "La razon es obligatorio")
        razon.length < 2 -> ValidationResult(false, "Minimo 2 caracteres")
        else -> ValidationResult(true)
    }
}