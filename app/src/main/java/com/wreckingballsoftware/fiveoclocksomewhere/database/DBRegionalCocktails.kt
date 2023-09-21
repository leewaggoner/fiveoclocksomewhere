package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "regional_cocktails",
    foreignKeys = [
        ForeignKey(
            entity = DBTimeZones::class,
            parentColumns = ["id"],
            childColumns = ["zone_id"],
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
    @ColumnInfo(name = "zone_id")
    val zoneId: Int,
    @ColumnInfo(name = "cocktail_id")
    val cocktailId: Int,
)
