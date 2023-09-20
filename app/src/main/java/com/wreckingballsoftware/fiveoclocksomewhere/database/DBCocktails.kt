package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class DBCocktails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val url: String,
)