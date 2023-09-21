package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "countries",
    foreignKeys = [
        ForeignKey(
            entity = DBTimeZones::class,
            parentColumns = ["id"],
            childColumns = ["zone_id"],
        )
    ]
)
data class DBCountries(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo("zone_id")
    val zoneId: Int,
)
