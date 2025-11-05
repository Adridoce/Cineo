package com.adridoce.cineo.data.response

import com.adridoce.cineo.domain.entity.MovieDetailEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<Genre>,
    @SerialName("poster_path") val posterUrl: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val rating: Float
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)

fun MovieDetailResponse.toDomain(): MovieDetailEntity {
    return MovieDetailEntity(
        id = id,
        title = title,
        overview = overview,
        tagline = tagline,
        genres = genres.map { it.name },
        posterUrl = "https://image.tmdb.org/t/p/w500${posterUrl}",
        releaseDate = releaseDate,
        rating = rating
    )
}