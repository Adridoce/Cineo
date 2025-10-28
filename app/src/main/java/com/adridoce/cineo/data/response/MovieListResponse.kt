package com.adridoce.cineo.data.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    val results: List<MovieResponse>
)
