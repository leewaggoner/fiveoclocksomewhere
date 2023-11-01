package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RegionalCocktailsDao {
    @Query("SELECT cocktails.id,cocktails.search_string FROM cocktails " +
            "INNER JOIN regional_cocktails ON cocktails.id=regional_cocktails.cocktail_id " +
            "WHERE regional_cocktails.place_id=:place")
    fun getAllCocktailsInPlace(place: Int): List<DBCocktails>
}
