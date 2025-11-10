package com.adridoce.cineo.domain.usecase

import com.adridoce.cineo.domain.repository.MovieTrailerRepository
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(
    val movieTrailerRepository: MovieTrailerRepository
) {
    suspend operator fun invoke(id: Int): String? {
        val videos=  movieTrailerRepository.getMovieTrailer(id)
        return videos.firstOrNull {
            it.site == "YouTube" && it.type == "Trailer"
        }?.key
    }
}