package com.adridoce.cineo.domain.entity

data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val genreIds: List<Int>,
    val posterUrl: String?,
    val releaseDate: String,
    val rating: Float
)
