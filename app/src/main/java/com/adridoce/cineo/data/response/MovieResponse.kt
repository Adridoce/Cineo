package com.adridoce.cineo.data.response

import com.adridoce.cineo.domain.entity.MovieEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("poster_path") val posterUrl: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val rating: Float
)

@Serializable
data class MovieListResponse(
    val results: List<MovieResponse>
)

fun MovieResponse.toDomain(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        genreIds = genreIds,
        posterUrl = "https://image.tmdb.org/t/p/w500${posterUrl}",
        releaseDate = releaseDate,
        rating = rating
    )
}
