package com.adridoce.cineo.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adridoce.cineo.presentation.core.components.SearchMoviesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = uiState.query,
                    onQueryChange = { viewModel.onQueryChange(it) },
                    onSearch = { viewModel.onSearchMovie() },
                    expanded = uiState.expanded,
                    onExpandedChange = { viewModel.onExpandedChange(it) },
                    placeholder = { Text("Buscar películas...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                    trailingIcon = {
                        if (uiState.query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    viewModel.onQueryChange("")
                                    viewModel.onExpandedChange(false)
                                }
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Borrar texto")
                            }
                        }
                    }
                )
            },
            expanded = uiState.expanded,
            onExpandedChange = { viewModel.onExpandedChange(it) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                SearchMoviesList(
                    movies = uiState.movies,
                    modifier = Modifier.fillMaxWidth(),
                    onMovieClick = { movie -> navigateToDetail(movie.id) }
                )
            }
        }
    }
}
