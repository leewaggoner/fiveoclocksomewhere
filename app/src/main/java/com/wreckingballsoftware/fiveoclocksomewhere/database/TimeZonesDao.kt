package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TimeZonesDao {
    @Query("SELECT * FROM time_zones")
    fun getAllTimeZones(): List<DBTimeZones>

    @Query("SELECT id FROM time_zones WHERE :zoneString=zone")
    fun getTimeZoneId(zoneString: String): Int
}
