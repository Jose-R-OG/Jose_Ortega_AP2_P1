package com.example.jose_ortega_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jose_ortega_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import com.example.jose_ortega_ap2_p1.domain.usecase.ObserveAmonestacionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(
    private val listarAmonestacionUseCase: ObserveAmonestacionUseCase,
    private val eliminarAmonestacionUseCase: DeleteAmonestacionUseCase
): ViewModel() {
    private val _state = MutableStateFlow(AmonestacionListUiState(isLoading = true))
    val state: StateFlow<AmonestacionListUiState> = _state.asStateFlow()

    init {
        loadAmonestacion()
    }

    fun onEvent(event: AmonestacionListUiEvent) {
        when(event){
            AmonestacionListUiEvent.Load -> loadAmonestacion()
            AmonestacionListUiEvent.Refresh -> loadAmonestacion()
            is AmonestacionListUiEvent.Delete -> onDelete(event.id)
            is AmonestacionListUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            AmonestacionListUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
            AmonestacionListUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            is AmonestacionListUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
        }
    }

    fun loadAmonestacion(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            listarAmonestacionUseCase().collectLatest { list -> _state.update { it.copy(isLoading = false, amonestaciones = list, message = null) } }
        }
    }

    private fun onDelete(id: Int){
        viewModelScope.launch {
            eliminarAmonestacionUseCase(id)
            onEvent(AmonestacionListUiEvent.ShowMessage("Eliminado"))
        }
    }
}