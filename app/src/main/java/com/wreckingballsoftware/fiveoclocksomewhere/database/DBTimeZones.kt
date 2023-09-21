package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_zones")
class DBTimeZones(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val zone: String,
)
