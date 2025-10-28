package com.adridoce.cineo.domain.usecase

import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(id: Int): MovieEntity {
        return movieDetailsRepository.getMovieDetails(id)
    }
}