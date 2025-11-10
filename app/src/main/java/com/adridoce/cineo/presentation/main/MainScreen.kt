@file:OptIn(ExperimentalMaterial3Api::class)

package com.adridoce.cineo.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adridoce.cineo.presentation.favorites.FavoritesScreen
import com.adridoce.cineo.presentation.home.HomeScreen
import com.adridoce.cineo.presentation.search.SearchScreen

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()

    val tabsList = listOf(
        NavItem(Icons.Default.Search, "Buscar"),
        NavItem(Icons.Default.Home, "Home"),
        NavItem(Icons.Default.Star, "Favoritos")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cineo") }
            )
        },
        bottomBar = {
            NavigationBar {
                tabsList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedTab,
                        onClick = {
                            viewModel.onSelectedTab(index)

                        },
                        icon = { Icon(item.icon, "") },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSurface,
                            selectedTextColor = MaterialTheme.colorScheme.onSurface,
                            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> SearchScreen(
                    navigateToDetail = { id -> navigateToDetail(id) }
                )

                1 -> HomeScreen(
                    navigateToDetail = { id -> navigateToDetail(id) }
                )

                2 -> FavoritesScreen(
                    navigateToDetail = {}
                )
            }
        }
    }
}

data class NavItem(
    val icon: ImageVector,
    val label: String
)