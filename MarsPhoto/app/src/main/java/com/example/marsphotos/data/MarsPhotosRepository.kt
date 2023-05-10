package com.example.marsphotos.data

import android.util.Log
import com.example.marsphotos.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class DefaultMarsPhotosRepository(
    private val marsApiService: MarsApiService
): MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        val res = marsApiService.getPhotos()
        Log.d("DEBUG", "${res.size}")
        return res
    }
}