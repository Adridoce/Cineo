package com.adridoce.cineo.presentation.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.adridoce.cineo.domain.entity.MovieEntity

@Composable
fun TrendingMoviesList(
    movies: List<MovieEntity>,
    listState: LazyListState,
    modifier : Modifier = Modifier,
    onMovieClick: (MovieEntity) -> Unit
) {
    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = movies,
            key = { _, movie -> movie.id }
        ) { index, movie ->
            MovieItem(
                movie = movie,
                modifier = modifier,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}

@Composable
fun SearchMoviesList(
    movies: List<MovieEntity>,
    onMovieClick: (MovieEntity) -> Unit,
    modifier:Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies){ movie ->
            MovieItem(
                movie = movie,
                modifier = modifier,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}