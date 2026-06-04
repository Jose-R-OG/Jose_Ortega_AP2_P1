package com.example.jose_ortega_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameListScreen(
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit,
    onDeleteBorrame: (Int) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Lista") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBorrame,
                modifier = Modifier.testTag("borrame_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { innerPadding ->
        BorrameListBody(
            modifier = Modifier.padding(innerPadding),
            onEditClick = onEditBorrame,
            onDeleteClick = onDeleteBorrame
        )
    }
}

@Composable
fun BorrameListBody(
    modifier: Modifier = Modifier,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    val listaDePrueba = listOf(1, 2, 3, 4, 5)

    if (listaDePrueba.isEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(listaDePrueba) { item ->
                BorrameItem(
                    borrameId = item,
                    onEdit = { onEditClick(item) },
                    onDelete = { onDeleteClick(item) }
                )
            }
        }
    } else {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No hay registros.")
        }

    }
}


@Composable
fun BorrameItem(
    borrameId: Int,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Registro Nombre #$borrameId",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Categoría o Tipo",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Fecha: 03/06/2026",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "RD$1,500.00",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier.testTag("btn_eliminar_$borrameId")
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar registro"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BorrameListScreenPreview() {
    BorrameListScreen(
        onAddBorrame = {},
        onEditBorrame = {},
        onDeleteBorrame = {}
    )
}


