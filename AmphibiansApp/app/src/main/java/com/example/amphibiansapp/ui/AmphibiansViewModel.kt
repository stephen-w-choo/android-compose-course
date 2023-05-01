package com.example.amphibiansapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/*
This layer contains the ViewModel and composable functions that display
the UiState coming from the ViewModel on the screen. The ViewModel is
in charge of exposing the screen UI state, and handling the business
logic in the UI layer and calling the business logic from other layers
of the hierarchy.

 */
sealed interface AmphibianUiState {
    object Success: AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibiansViewModel: ViewModel() {

    // The state variable
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {

    }

    private fun getAmphibianData() {
        // TODO - get the data once the Data layer is implemented
    }

}