package com.example.jose_ortega_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jose_ortega_ap2_p1.domain.model.Amonestacion

@Composable
fun AmonestacionListScreen(
    viewModel: AmonestacionListViewModel = hiltViewModel(),
    onAddAmonestacion: () -> Unit,
    onEditAmonestacion: (Int) -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    AmonestacionListBody (
        state = state,
        onEvent = viewModel::onEvent,
        onAddClick = onAddAmonestacion,
        onEditClick = onEditAmonestacion
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionListBody(
    state: AmonestacionListUiState,
    onEvent: (AmonestacionListUiEvent) -> Unit,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(AmonestacionListUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                modifier = Modifier.testTag("am_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Amonestacion"
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()){
            if(state.isLoading){
                CircularProgressIndicator(Modifier.align(Alignment.Center).testTag("Cargando"))
            }else{
                if(state.amonestaciones.isEmpty()){
                    Text(
                        text = "No hay Amonestaciones",
                        modifier = Modifier.align(Alignment.Center).testTag("Mensaje_Vacio"),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }else{
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.amonestaciones,
                            key = { it.amonestacionId }
                        ){ amonestacion ->
                            EmpleadoItem(
                                amonestacion = amonestacion,
                                onDelete = {onEvent(AmonestacionListUiEvent.Delete(amonestacion.amonestacionId))},
                                onEdit = {onEditClick(amonestacion.amonestacionId)}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmpleadoItem(
    amonestacion: Amonestacion,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().clickable{onEdit()}) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = amonestacion.nombres,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = amonestacion.razon,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "RD$${amonestacion.monto}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier.testTag("btn_eliminar_${amonestacion.amonestacionId}")
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar amonestacion"
                )
            }
        }
    }
}


