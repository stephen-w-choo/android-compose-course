package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.network.AppContainer
import com.example.marsphotos.network.DefaultAppContainer

class MarsPhotosApplication: Application() {
    lateinit var container: DefaultAppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}