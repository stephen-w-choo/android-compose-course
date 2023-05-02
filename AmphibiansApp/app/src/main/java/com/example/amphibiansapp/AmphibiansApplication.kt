package com.example.amphibiansapp

import android.app.Application
import com.example.amphibiansapp.network.DefaultAppContainer

class AmphibiansApplication: Application() {
    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}