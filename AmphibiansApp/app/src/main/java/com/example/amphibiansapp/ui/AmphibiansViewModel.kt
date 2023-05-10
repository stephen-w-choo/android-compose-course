package com.example.amphibiansapp.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.data.AmphibiansDataRepository
import com.example.amphibiansapp.network.AmphibianData
import com.example.amphibiansapp.network.DefaultAppContainer
import kotlinx.coroutines.launch
import java.io.IOException

/*
This layer contains the ViewModel and composable functions that display
the UiState coming from the ViewModel on the screen. The ViewModel is
in charge of exposing the screen UI state, and handling the business
logic in the UI layer and calling the business logic from other layers
of the hierarchy.

 */

class AmphibiansApplication: Application() {
    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
sealed interface AmphibianUiState {
    data class Success(
        val amphibiansData: List<AmphibianData>
    ): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibiansViewModel(
    private val amphibiansDataRepository: AmphibiansDataRepository
)
    : ViewModel() {
    // The state variable
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    private fun getAmphibiansData() {
        viewModelScope.launch {
            amphibianUiState = try {
                val apiResult = amphibiansDataRepository.getAmphibianData()
                AmphibianUiState.Success(apiResult)
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }

    /*
    * We implement a ViewModelProvider.Factory object, which lets us use dependency injection.
    * Normally you wouldn't be able to pass the repository into the ViewModel when it's created
    * - it's not supposed to take any variables.
    * The Factory pattern is a pattern used to create objects.
    * The MarsViewModel.Factory object uses the application container to retrieve the
    * marsPhotosRepository, and then passes this repository to the ViewModel when the
    * object is created.
    */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as AmphibiansApplication
                val amphibiansDataRepository = application.container.amphibiansDataRepository
                AmphibiansViewModel(amphibiansDataRepository = amphibiansDataRepository)
            }
        }
    }
}