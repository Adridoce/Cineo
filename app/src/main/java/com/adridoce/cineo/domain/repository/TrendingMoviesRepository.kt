package com.adridoce.cineo.domain.repository

import com.adridoce.cineo.domain.entity.MovieEntity

interface TrendingMoviesRepository {
    suspend fun getTrendingMovies(timeWindow: String): List<MovieEntity>
}