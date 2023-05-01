/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>): MarsUiState
    object Error: MarsUiState
    object Loading: MarsUiState
}

class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository
) : ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState = MarsUiState.Loading
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API
     */

    private fun getMarsPhotos() {
        viewModelScope.launch {
            // launches the API request service in a coroutine to maintain non-blocking behaviour
            try {
                val result = marsPhotosRepository.getMarsPhotos()
                Log.d("DEBUG", "success")
                marsUiState = MarsUiState.Success(result)
            }
            catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
    }

    /**
     * Because the Android framework does not allow a ViewModel to be passed values
     * in the constructor when created, we implement a ViewModelProvider.Factory object, which lets us get around this limitation.
     * The Factory pattern is a creational pattern used to create objects.
     * The MarsViewModel.Factory object uses the application container to retrieve the
     * marsPhotosRepository, and then passes this repository to the ViewModel when the
     * object is created.
     *
     * This is opposed to how we would normallyu create a viewmodel like so:
     * class MyViewModel: ViewModel(){} - no arguments are passed in here, because the viewmodel is just a type annotation
     */

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
