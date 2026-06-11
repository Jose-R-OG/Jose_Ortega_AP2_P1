package com.example.jose_ortega_ap2_p1.presentation.form

import java.time.LocalDate

interface AmonestacionFormUiEvent {
    data class Load(val id: Int?): AmonestacionFormUiEvent
    data class NombresChanged(val value: String): AmonestacionFormUiEvent
    data class RazonChanged(val value: String): AmonestacionFormUiEvent
    data class MontoChanged(val value: String): AmonestacionFormUiEvent
    data object Save: AmonestacionFormUiEvent
    data object Delete: AmonestacionFormUiEvent
}