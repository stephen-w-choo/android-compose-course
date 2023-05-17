package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppDataContainer

class BusScheduleApplication: Application() {
    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}