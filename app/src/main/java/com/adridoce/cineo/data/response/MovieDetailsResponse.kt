package com.adridoce.cineo.data.response

import com.adridoce.cineo.domain.entity.MovieEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    @SerialName("poster_path") val posterUrl: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val rating: Float
)

fun MovieDetailsResponse.toDomain(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        genreIds = listOf(),
        posterUrl = "https://image.tmdb.org/t/p/w500${posterUrl}",
        releaseDate = releaseDate,
        rating = rating
    )
}