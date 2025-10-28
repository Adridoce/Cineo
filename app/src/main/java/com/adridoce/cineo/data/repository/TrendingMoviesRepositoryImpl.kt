package com.adridoce.cineo.data.repository

import com.adridoce.cineo.data.datasource.api.TmdbApiService
import com.adridoce.cineo.data.response.toDomain
import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.repository.TrendingMoviesRepository
import javax.inject.Inject

class TrendingMoviesRepositoryImpl @Inject constructor(
    val api: TmdbApiService
) : TrendingMoviesRepository {

    override suspend fun getTrendingMovies(timeWindow: String): List<MovieEntity> {
        val response = api.getTrendingMovies(timeWindow)
        return response.results.map { it.toDomain() }
    }
}