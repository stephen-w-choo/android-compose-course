package com.example.marsphotos.network

import com.example.marsphotos.data.MarsPhotosRepository

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}