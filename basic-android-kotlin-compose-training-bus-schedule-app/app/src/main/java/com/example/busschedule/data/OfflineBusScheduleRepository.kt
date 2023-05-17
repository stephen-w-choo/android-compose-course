package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(
    val busScheduleDao: BusScheduleDao
) {
    fun getAllBusStopsStream(): Flow<List<Schedule>> {
        return busScheduleDao.getAllBusStops()
    }

    fun getBusStopStream(stopName: String): Flow<Schedule> {
        return busScheduleDao.getBusStop(stopName)
    }
}