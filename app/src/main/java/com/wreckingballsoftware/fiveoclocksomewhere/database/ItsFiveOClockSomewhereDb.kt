package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DBCocktails::class,
        DBTimeZones::class,
        DBCountries::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class ItsFiveOClockSomewhereDb : RoomDatabase() {
    abstract fun getCocktailsDao(): CocktailsDao

    abstract fun getTimeZonesDao(): TimeZonesDao

    abstract fun getCountriesDao(): CountriesDao
}
