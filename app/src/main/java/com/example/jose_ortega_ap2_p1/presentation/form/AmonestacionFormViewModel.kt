package com.example.jose_ortega_ap2_p1.presentation.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion
import com.example.jose_ortega_ap2_p1.domain.repository.AmonestacionRepository
import com.example.jose_ortega_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import com.example.jose_ortega_ap2_p1.domain.usecase.GetAmonestacionUseCase
import com.example.jose_ortega_ap2_p1.domain.usecase.UpsertAmonestacionUseCase
import com.example.jose_ortega_ap2_p1.presentation.navigation.Screen
import com.example.registrodeocupaciones.domain.empleado.usecase.validarNombres
import com.example.registrodeocupaciones.domain.empleado.usecase.validarRazon
import com.example.registrodeocupaciones.domain.empleado.usecase.validarmonto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionFormViewModel @Inject constructor(
    private val repository: AmonestacionRepository,
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val eliminarAmonestacionUseCase: DeleteAmonestacionUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val routeArgs = savedStateHandle.toRoute<Screen.BorrameForm>()
    private val amonestacionId: Int = routeArgs.borrameId

    private val _state = MutableStateFlow(AmonestacionFormUiState())
    val state: StateFlow<AmonestacionFormUiState> = _state.asStateFlow()

    init {
        loadAmonestacion(amonestacionId)
    }

    fun onEvent(event: AmonestacionFormUiEvent){
        when(event){
            is AmonestacionFormUiEvent.Load -> loadAmonestacion(event.id)
            is AmonestacionFormUiEvent.NombresChanged -> _state.update { it.copy(nombres = event.value, nombresError = null) }
            is AmonestacionFormUiEvent.RazonChanged -> _state.update { it.copy(razon = event.value, razonError = null) }
            is AmonestacionFormUiEvent.MontoChanged -> _state.update { it.copy(monto = event.value, montoError = null) }
            AmonestacionFormUiEvent.Save -> onSave()
            AmonestacionFormUiEvent.Delete -> onDelete()
        }
    }

    private fun loadAmonestacion(id: Int?){
        if(id == null || id == 0){
            _state.update { it.copy(isNew = true, amonestacionId = null) }
            return
        }

        viewModelScope.launch {
            val amonestacion = getAmonestacionUseCase(id)
            if(amonestacion != null){
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacion.amonestacionId,
                        nombres = amonestacion.nombres,
                        razon = amonestacion.razon,
                        monto = amonestacion.monto.toString()
                    )
                }
            }else{
                _state.update { it.copy(isNew = true, amonestacionId = null) }
            }
        }
    }

    private fun onSave(){
        val nombres = state.value.nombres
        val razon = state.value.razon
        val montoText = state.value.monto

        val nombresValidation = validarNombres(nombres)
        val razonValidation = validarRazon(razon)
        val montoValidation = validarmonto(montoText)

        if( !nombresValidation.isValid || !razonValidation.isValid || !montoValidation.isValid){
            _state.update {
                it.copy(
                    nombresError = nombresValidation.error,
                    razonError = razonValidation.error,
                    montoError = montoValidation.error
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val amonestacion = Amonestacion(
                amonestacionId = state.value.amonestacionId ?: 0,
                nombres = nombres,
                razon = razon,
                monto = montoText.toDouble()
            )

            val result = upsertAmonestacionUseCase(amonestacion)

            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId,
                        isNew = false
                    )
                }
            }.onFailure { _state.update { it.copy(isSaving = false) } }
        }
    }

    private fun onDelete(){
        val id = state.value.amonestacionId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            eliminarAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }

}