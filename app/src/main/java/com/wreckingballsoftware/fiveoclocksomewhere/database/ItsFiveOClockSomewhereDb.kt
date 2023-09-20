package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBCocktails::class], version = 1, exportSchema = false)
abstract class ItsFiveOClockSomewhereDb : RoomDatabase() {
    abstract fun getCocktailsDao(): CocktailsDao
}