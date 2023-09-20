package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.database.CocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCocktails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailsRepo(private val cocktailsDao: CocktailsDao) {
    suspend fun getAllCocktails(): List<DBCocktails> = withContext(Dispatchers.IO) {
        cocktailsDao.getAllCocktails()
    }
}