package com.adridoce.cineo.data.repository

import com.adridoce.cineo.data.datasource.api.TmdbApiService
import com.adridoce.cineo.data.response.toDomain
import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.repository.SearchMovieRepository
import javax.inject.Inject

class SearchMovieRepositoryImpl @Inject constructor(
    val api: TmdbApiService
) : SearchMovieRepository {

    override suspend fun searchMovie(movie: String): List<MovieEntity> {
        val response = api.searchMovie(movie)
        return response.results.map { it.toDomain() }
    }
}

