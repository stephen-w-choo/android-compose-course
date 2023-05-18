package com.example.busschedule

import android.app.Application


class BusScheduleApplication: Application() {
//    Originally I had the database in a container
//    but I cut it out and initialised the database directly
//    lateinit var container: DataContainer
//
//    override fun onCreate() {
//        super.onCreate()
//        container = AppDataContainer(this)
//    }

    val database: BusScheduleDatabase by lazy { BusScheduleDatabase.getDatabase(this) }
}