package com.adridoce.cineo.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adridoce.cineo.domain.entity.MovieEntity
import com.adridoce.cineo.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onQueryChange(query: String) {
        _uiState.update { state ->
            state.copy(query = query)
        }
    }

    fun onExpandedChange(expanded: Boolean) {
        _uiState.update { state ->
            state.copy(expanded = expanded)
        }
    }

    fun onSearchMovie(){
        val query = _uiState.value.query.trim()
        if(query.isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val movies = searchMovieUseCase(query)
                _uiState.update { it.copy(isLoading = false, movies = movies) }
            }catch (e:Exception){
                _uiState.update { it.copy(isLoading = false, error = "Error al buscar") }
            }
        }
    }
}

data class SearchUiState(
    val query: String = "",
    val expanded: Boolean = false,
    val isLoading:Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val error: String? = null
)