package com.adridoce.cineo.data.datasource.api

import com.adridoce.cineo.data.response.MovieDetailResponse
import com.adridoce.cineo.data.response.MovieListResponse
import com.adridoce.cineo.data.response.MovieTrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("trending/movie/{time_window}")            // Peliculas en tendencias
    suspend fun getTrendingMovies(
        @Path("time_window") time_window: String,  // day o week
        @Query("api_key") apiKey: String = "8e7af49fafd448d939f624a4f0dbc934",
        @Query("language") language: String = "es-ES"
    ): MovieListResponse

    @GET("search/movie")                            // Buscar una pelicula concreta
    suspend fun searchMovie(
        @Query("query") movie: String,
        @Query("api_key") apiKey: String = "8e7af49fafd448d939f624a4f0dbc934",
        @Query("language") language: String = "es-ES"
    ): MovieListResponse

    @GET("movie/{movie_id}")                        // Detalles de una pelicula concreta
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "8e7af49fafd448d939f624a4f0dbc934",
        @Query("language") language: String = "es-ES"
    ): MovieDetailResponse

    @GET("movie/{movie_id}/videos")                 // Trailer de pelicula
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey:String = "8e7af49fafd448d939f624a4f0dbc934",
        @Query("language") language: String = "es-ES"
    ): MovieTrailerResponse
}