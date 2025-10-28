package com.adridoce.cineo.domain.repository

import com.adridoce.cineo.domain.entity.MovieEntity

interface SearchMovieRepository {
    suspend fun searchMovie(movie: String): List<MovieEntity>
}