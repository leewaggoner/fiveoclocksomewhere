package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.database.CocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.CountriesDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCocktails
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCountries
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBTimeZones
import com.wreckingballsoftware.fiveoclocksomewhere.database.RegionalCocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.TimeZonesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailsRepo(
    private val cocktailsDao: CocktailsDao,
    private val timeZonesDao: TimeZonesDao,
    private val countriesDao: CountriesDao,
    private val regionalCocktailsDao: RegionalCocktailsDao,
    ) {
    suspend fun getAllCocktails(): List<DBCocktails> = withContext(Dispatchers.IO) {
        cocktailsDao.getAllCocktails()
    }

    suspend fun getAllTimeZones(): List<DBTimeZones> = withContext(Dispatchers.IO) {
        timeZonesDao.getAllTimeZones()
    }

    suspend fun getAllCountriesInZone(timeZone: Int): List<DBCountries> = withContext(Dispatchers.IO) {
        countriesDao.getAllCountriesInZone(timeZone)
    }

    suspend fun getAllCocktailsInZone(timeZone: Int): List<DBCocktails> = withContext(Dispatchers.IO) {
        regionalCocktailsDao.getAllCocktailsInZone(timeZone)
    }
}
