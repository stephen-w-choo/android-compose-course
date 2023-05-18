package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    // Retrieve all items in the database
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAllBusStops(): Flow<List<Schedule>>

    // Retrieve single item if given a bus stop name
    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getBusStop(stopName: String): Flow<List<Schedule>>
}

// my question is about client facing - people oriented roles
// vs a more technical role
// do you often see people transition within technical roles
// if you're recruiting