package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RegionalCocktailsDao {
    @Query("SELECT * FROM cocktails " +
            "INNER JOIN regional_cocktails ON cocktails.id=regional_cocktails.cocktail_id " +
            "WHERE regional_cocktails.zone_id=:timeZone")
    fun getAllCocktailsInZone(timeZone: Int): List<DBCocktails>
}
