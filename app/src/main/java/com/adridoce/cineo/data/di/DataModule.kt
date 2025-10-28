package com.adridoce.cineo.data.di

import com.adridoce.cineo.data.datasource.api.TmdbApiService
import com.adridoce.cineo.data.repository.MovieDetailsRepositoryImpl
import com.adridoce.cineo.data.repository.SearchMovieRepositoryImpl
import com.adridoce.cineo.data.repository.TrendingMoviesRepositoryImpl
import com.adridoce.cineo.domain.repository.MovieDetailsRepository
import com.adridoce.cineo.domain.repository.SearchMovieRepository
import com.adridoce.cineo.domain.repository.TrendingMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideTrendingMoviesRepository(api: TmdbApiService): TrendingMoviesRepository {
        return TrendingMoviesRepositoryImpl(api)
    }

    @Provides
    fun provideSearchMovieRepository(api: TmdbApiService): SearchMovieRepository {
        return SearchMovieRepositoryImpl(api)
    }

    @Provides
    fun provideMovieDetailsRepository(api: TmdbApiService): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(api)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): TmdbApiService {
        return retrofit.create(TmdbApiService::class.java)
    }

    @Provides
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}