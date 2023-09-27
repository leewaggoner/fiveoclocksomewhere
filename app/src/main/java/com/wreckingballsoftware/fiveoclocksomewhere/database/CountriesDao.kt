package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries WHERE time_zone IN (:zones)")
    fun getAllCountriesInZones(zones: List<Int>): List<DBCountries>
}
