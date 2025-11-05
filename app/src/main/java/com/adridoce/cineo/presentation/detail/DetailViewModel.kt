package com.adridoce.cineo.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adridoce.cineo.domain.entity.MovieDetailEntity
import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    private val movieId: Int = checkNotNull(savedStateHandle["id"])

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val movie = getMovieDetailsUseCase(movieId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false, movie = movie, errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    movie = null,
                    errorMessage = "No se pudo cargar la información"
                )
            }
        }
    }

}

data class DetailUiState(
    val isLoading: Boolean = true,
    val movie: MovieDetailEntity? = null,
    val errorMessage: String? = null
)