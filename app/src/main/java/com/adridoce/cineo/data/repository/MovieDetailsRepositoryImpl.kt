package com.adridoce.cineo.data.repository

import com.adridoce.cineo.data.datasource.api.TmdbApiService
import com.adridoce.cineo.data.response.toDomain
import com.adridoce.cineo.domain.entity.MovieDetailEntity
import com.adridoce.cineo.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    val api: TmdbApiService
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(id: Int): MovieDetailEntity {
        val response = api.getMovieDetails(id)
        return response.toDomain()
    }
}