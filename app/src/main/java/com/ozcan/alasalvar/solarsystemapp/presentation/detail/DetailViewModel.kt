package com.ozcan.alasalvar.solarsystemapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import com.ozcan.alasalvar.solarsystemapp.domain.repository.Repository
import com.ozcan.alasalvar.solarsystemapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val loading: Boolean = true,
    val success: Planet? = null,
    val error: String? = null
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        savedStateHandle.get<Int>("position")?.let { position ->
            getPlanet(position = position)
        }
    }

    private fun getPlanet(position: Int) = viewModelScope.launch {

        uiState.copy(loading = true, success = null, error = null)

        when (val result = repository.getPlanet(position)) {
            is Resource.Error -> {
                uiState =
                    uiState.copy(loading = false, success = null, error = result.exception.message)
            }
            is Resource.Success -> {
                uiState =
                    uiState.copy(loading = false, success = result.data, error = null)

            }
        }
    }
}