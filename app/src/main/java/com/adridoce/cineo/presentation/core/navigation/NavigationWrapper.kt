package com.adridoce.cineo.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adridoce.cineo.presentation.detail.DetailScreen
import com.adridoce.cineo.presentation.main.MainScreen

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main) {
        composable<Main> {
            MainScreen(
                navigateToDetail = { id -> navController.navigate(Detail(id = id)) }
            )
        }

        composable<Detail> { DetailScreen() }
    }
}