package com.adridoce.cineo.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adridoce.cineo.presentation.core.components.MovieDetailItem
import com.adridoce.cineo.presentation.core.components.YoutubeVideoPlayer

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
//                TODO: Implementar
            }

//            uiState.showTrailer && uiState.trailerKey != null -> {
//                YoutubeVideoPlayer(
//                    key = uiState.trailerKey!!,
//                    onCloseTrailer = { viewModel.onCloseTrailer() }
//                )
//            }

            uiState.movie != null -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MovieDetailItem(
                        movie = uiState.movie!!,
                        onPlayTrailer = { viewModel.onPlayTrailer() }
                    )

                    if (uiState.showTrailer && uiState.trailerKey != null) {
                        YoutubeVideoPlayer(
                            key = uiState.trailerKey!!,
                            onCloseTrailer = { viewModel.onCloseTrailer() }
                        )
                    }
                }
            }
        }
    }
}