package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlacesDao {
    @Query("SELECT * FROM places WHERE time_zone IN (:zones)")
    fun getAllPlacesInZones(zones: List<Int>): List<DBPlaces>
}
