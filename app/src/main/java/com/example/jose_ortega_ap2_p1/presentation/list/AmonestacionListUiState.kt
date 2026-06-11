package com.example.jose_ortega_ap2_p1.presentation.list

import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion

data class AmonestacionListUiState (
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null,
    val error: String? = null
    )
