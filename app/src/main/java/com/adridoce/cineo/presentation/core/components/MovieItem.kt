package com.adridoce.cineo.presentation.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.adridoce.cineo.domain.entity.MovieDetailEntity
import com.adridoce.cineo.domain.entity.MovieEntity

@Composable
fun MovieListItem(
    movie: MovieEntity,
    modifier: Modifier = Modifier,
    onClick: (MovieEntity) -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick(movie) },
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(6.dp)
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "⭐",
                    fontSize = 14.sp,
                    color = Color(0xFFFFD700)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = String.format("%.1f", movie.rating),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun MovieDetailItem(movie: MovieDetailEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = "movie poster",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Text(movie.title, color = MaterialTheme.colorScheme.onBackground)
        Text(movie.tagline, color = MaterialTheme.colorScheme.onBackground)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
        ) {
            Text(movie.genres.joinToString(" | "), color = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.weight(1f))
            Text(movie.releaseDate, color = MaterialTheme.colorScheme.onBackground)
        }
        Text(movie.overview, color = MaterialTheme.colorScheme.onBackground)
        Text("${movie.rating}/10", color = MaterialTheme.colorScheme.onBackground)
    }
}