package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CocktailsDao {
    @Query("SELECT * FROM cocktails")
    fun getAllCocktails(): List<DBCocktails>
}