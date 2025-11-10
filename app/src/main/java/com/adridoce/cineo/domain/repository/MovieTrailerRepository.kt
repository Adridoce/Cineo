package com.adridoce.cineo.domain.repository

import com.adridoce.cineo.domain.entity.TrailerEntity

interface MovieTrailerRepository {
    suspend fun getMovieTrailer(id: Int): List<TrailerEntity>
}