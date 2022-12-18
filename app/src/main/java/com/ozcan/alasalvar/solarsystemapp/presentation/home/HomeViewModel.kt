package com.ozcan.alasalvar.solarsystemapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import com.ozcan.alasalvar.solarsystemapp.domain.repository.Repository
import com.ozcan.alasalvar.solarsystemapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val loading: Boolean = true,
    val success: List<Planet> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: State<HomeUiState> = _uiState

    init {
        getPlanets()
    }
    private fun getPlanets() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true)
        when (val result = repository.getPlanets()) {
            is Resource.Error -> {
                _uiState.value =
                    _uiState.value.copy(loading = false, error = result.exception.message)
            }
            is Resource.Success -> {
                _uiState.value =
                    _uiState.value.copy(loading = false, success = result.data, error = null)
            }
        }
    }
}