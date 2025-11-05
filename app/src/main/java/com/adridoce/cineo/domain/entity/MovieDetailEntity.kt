package com.adridoce.cineo.domain.entity

data class MovieDetailEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<String>,
    val posterUrl: String?,
    val releaseDate: String,
    val rating: Float
)