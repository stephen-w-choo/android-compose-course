package com.example.dessertclicker.model

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DessertViewModel : ViewModel() {
    // private ui state, mutable state flow triggers recomposition
    // whenever the dessert ui state variables change
    // private ensures only changes are made from the viewmodel
    // exposes values from the state via the .value subclass
    private val _uiState = MutableStateFlow(DessertUiState())
    private val desserts = dessertList

    // read only state that is exposed to the public
    // state values can be read directly from uiState, does not require .value
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        var currentDessertPrice = desserts[_uiState.value.currentDessertIndex].price
        var updatedRevenue = _uiState.value.revenue + currentDessertPrice
        var updatedDessertsSold = _uiState.value.dessertsSold + 1
        // Show the next dessert
        var dessertIndexToShow = determineDessertIndexToShow(desserts, updatedDessertsSold)
        _uiState.update { currentState ->
            currentState.copy(
                revenue = updatedRevenue,
                dessertsSold = updatedDessertsSold,
                currentDessertIndex = dessertIndexToShow
            )
        }
    }

    fun determineDessertIndexToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Int {
        var dessertIndexToShow = 0
        for ((index, dessert) in desserts.withIndex()) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertIndexToShow = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return dessertIndexToShow
    }

}