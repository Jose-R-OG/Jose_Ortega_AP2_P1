package com.example.jose_ortega_ap2_p1.presentation.form

data class AmonestacionFormUiState (
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val razon: String = "",
    val monto: String = "",
    val nombresError: String? = null,
    val razonError: String? = null,
    val montoError: String? = null,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)