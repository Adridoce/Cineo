package com.adridoce.cineo.domain.repository

import com.adridoce.cineo.domain.entity.MovieDetailEntity
import com.adridoce.cineo.domain.entity.MovieEntity

interface MovieDetailsRepository {

    suspend fun getMovieDetails(id:Int): MovieDetailEntity
}