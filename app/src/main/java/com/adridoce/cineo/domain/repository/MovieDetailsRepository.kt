package com.adridoce.cineo.domain.repository

import com.adridoce.cineo.domain.entity.MovieDetailEntity

interface MovieDetailsRepository {

    suspend fun getMovieDetails(id:Int): MovieDetailEntity
}