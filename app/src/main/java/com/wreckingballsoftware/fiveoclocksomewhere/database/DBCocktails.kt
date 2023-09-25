package com.wreckingballsoftware.fiveoclocksomewhere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class DBCocktails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo("search_string")
    val searchString: String,
)
