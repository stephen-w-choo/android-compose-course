package com.example.busschedule.data

import android.content.Context
import com.example.busschedule.BusScheduleDatabase

interface DataContainer {
    val busScheduleRepository: OfflineBusScheduleRepository
}

class AppDataContainer(private val context: Context): DataContainer {
    override val busScheduleRepository: OfflineBusScheduleRepository by lazy {
        OfflineBusScheduleRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
    }
}