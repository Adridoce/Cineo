package com.adridoce.cineo.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adridoce.cineo.presentation.core.components.TrendToggle
import com.adridoce.cineo.presentation.core.components.TrendingMoviesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail:(Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val trendListState = rememberLazyListState()

    LaunchedEffect(uiState.trendFilter) {
        trendListState.animateScrollToItem(0)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tendencias",
                textAlign = TextAlign.Start,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.weight(1f))
            TrendToggle(
                selected = uiState.trendFilter,
                onSelect = { viewModel.setFilter(it) }
            )
        }
        Spacer(Modifier.height(6.dp))
        TrendingMoviesList(
            movies = uiState.movies,
            listState = trendListState,
            modifier = Modifier
                .width(150.dp)
                .height(300.dp),
            onMovieClick = { movie -> navigateToDetail(movie.id) }
        )
    }
}
