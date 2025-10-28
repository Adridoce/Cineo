package com.adridoce.cineo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.usecase.GetTrendingMoviesUseCase
import com.adridoce.cineo.presentation.core.components.TrendFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadTrendingMovies()
    }

    private fun loadTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val timeWindow = when (_uiState.value.trendFilter) {
                    TrendFilter.DAILY -> "day"
                    TrendFilter.WEEKLY -> "week"
                }
                val movies = getTrendingMoviesUseCase(timeWindow)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        movies = movies
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = "Error al cargar las películas"
                    )
                }
            }
        }
    }

    fun setFilter(filter: TrendFilter) {
        _uiState.update { state ->
            state.copy(trendFilter = filter)
        }
        loadTrendingMovies()
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val trendFilter: TrendFilter = TrendFilter.DAILY,
    val errorMessage: String = ""
)



