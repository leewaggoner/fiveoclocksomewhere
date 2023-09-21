package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries WHERE zone_id=:zoneId")
    fun getAllCountriesInZone(zoneId: Int): List<DBCountries>
}
