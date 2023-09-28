package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DBCocktails::class,
        DBTimeZones::class,
        DBPlaces::class,
        DBRegionalCocktails::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class ItsFiveOClockSomewhereDb : RoomDatabase() {
    abstract fun getTimeZonesDao(): TimeZonesDao

    abstract fun getPlacesDao(): PlacesDao

    abstract fun getRegionalCocktails(): RegionalCocktailsDao
}
