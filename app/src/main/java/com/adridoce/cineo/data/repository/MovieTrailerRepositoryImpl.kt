package com.adridoce.cineo.data.repository

import com.adridoce.cineo.data.datasource.api.TmdbApiService
import com.adridoce.cineo.data.response.toDomain
import com.adridoce.cineo.domain.entity.TrailerEntity
import com.adridoce.cineo.domain.repository.MovieTrailerRepository
import javax.inject.Inject

class MovieTrailerRepositoryImpl @Inject constructor(
    val api: TmdbApiService
): MovieTrailerRepository {

    override suspend fun getMovieTrailer(id: Int): List<TrailerEntity> {
        val response = api.getMovieTrailer(id)
        return response.results.map { it.toDomain() }
    }
}