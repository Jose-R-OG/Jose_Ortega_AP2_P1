package com.example.jose_ortega_ap2_p1.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jose_ortega_ap2_p1.presentation.form.AmonestacionFormScreen
import com.example.jose_ortega_ap2_p1.presentation.list.AmonestacionListScreen
import com.example.jose_ortega_ap2_p1.presentation.list.AmonestacionListUiEvent


@Composable
fun MineNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
            AmonestacionListScreen(
                onAddAmonestacion
                = { navController.navigate(Screen.BorrameForm(borrameId = 0)) },
                onEditAmonestacion
                = { id -> navController.navigate(Screen.BorrameForm(borrameId = id)) }
            )
        }

        composable<Screen.BorrameForm> {
            AmonestacionFormScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }


    }
}