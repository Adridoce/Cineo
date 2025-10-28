package com.adridoce.cineo.domain.usecase

import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.repository.SearchMovieRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    val searchMovieRepository: SearchMovieRepository
) {
    suspend operator fun invoke(movie:String): List<MovieEntity>{
        return searchMovieRepository.searchMovie(movie)
    }
}