package com.adridoce.cineo.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val movie = getMovieDetailsUseCase(movieId)
            _uiState.update { it.copy(movie = movie) }
        }
    }

}

data class DetailUiState(
    val movie: MovieEntity? = null // TODO: Revisar si es necesario que sea nullable
)