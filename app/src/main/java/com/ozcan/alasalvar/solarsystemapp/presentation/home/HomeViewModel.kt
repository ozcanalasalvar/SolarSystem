package com.ozcan.alasalvar.solarsystemapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getPlanets()
    }

    private fun getPlanets() = viewModelScope.launch {
        uiState = uiState.copy(loading = true)
        when (val result = repository.getPlanets()) {
            is Resource.Error -> {
                uiState = uiState.copy(loading = false, error = result.exception.message)
            }
            is Resource.Success -> {
                uiState = uiState.copy(loading = false, success = result.data, error = null)
            }
        }
    }
}