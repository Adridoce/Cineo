package com.adridoce.cineo.domain.usecase

import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.repository.TrendingMoviesRepository
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    val trendingMoviesRepository: TrendingMoviesRepository
) {
    suspend operator fun invoke(timeWindow: String): List<MovieEntity> {
        return trendingMoviesRepository.getTrendingMovies(timeWindow)
    }
}