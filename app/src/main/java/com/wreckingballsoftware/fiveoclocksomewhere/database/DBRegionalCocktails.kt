package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "regional_cocktails",
    foreignKeys = [
        ForeignKey(
            entity = DBPlaces::class,
            parentColumns = ["id"],
            childColumns = ["place_id"],
        ),
        ForeignKey(
            entity = DBCocktails::class,
            parentColumns = ["id"],
            childColumns = ["cocktail_id"],
        )
    ]
)
data class DBRegionalCocktails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "place_id", index = true)
    val placeId: Int,
    @ColumnInfo(name = "cocktail_id", index = true)
    val cocktailId: Int,
)
