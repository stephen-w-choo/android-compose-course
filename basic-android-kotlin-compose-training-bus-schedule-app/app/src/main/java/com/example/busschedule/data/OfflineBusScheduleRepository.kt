package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

// this is also unnecessary - we can just pass in the Dao directly

//class OfflineBusScheduleRepository(
//    val busScheduleDao: BusScheduleDao
//) {
//    fun getAllBusStopsStream(): Flow<List<Schedule>> {
//        return busScheduleDao.getAllBusStops()
//    }
//
//    fun getBusStopStream(stopName: String): Flow<Schedule> {
//        return busScheduleDao.getBusStop(stopName)
//    }
//}