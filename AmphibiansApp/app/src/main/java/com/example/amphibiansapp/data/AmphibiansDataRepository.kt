package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.AmphibianApiService
import com.example.amphibiansapp.network.AmphibianData

// the tutorial seems to like splitting things into classes and interfaces
// rather than just declaring the class directly - I am unsure why
// I'm choosing to mimic these patterns, but I haven't seen a use for them yet

// as a way of annotating the test repository maybe? Like self documenting code
// You can declare a fake data repository with the type of the interface
// but you could easily just declare a new class that doesn't use the interface
// It doesn't seem to affect overall function

interface AmphibiansDataRepository {
    suspend fun getAmphibianData(): List<AmphibianData>
}

class DefaultAmphibiansDataRepository(
    private val amphibianApiService: AmphibianApiService
): AmphibiansDataRepository {
    override suspend fun getAmphibianData(): List<AmphibianData> {
        return amphibianApiService.getData()
    }
}

