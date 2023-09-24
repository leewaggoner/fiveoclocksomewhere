package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.database.CocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCocktails
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBResponse
import com.wreckingballsoftware.fiveoclocksomewhere.database.RegionalCocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailsRepo(
    private val timeZonesRepo: TimeZonesRepo,
    private val cocktailsDao: CocktailsDao,
    private val regionalCocktailsDao: RegionalCocktailsDao,
    ) {
    val chosenCocktailData: DBCocktails? = null
    suspend fun getAllCocktails(): List<DBCocktails> = withContext(Dispatchers.IO) {
        cocktailsDao.getAllCocktails()
    }

    suspend fun getCocktailFromWhereIts5OClock(): DBResponse = withContext(Dispatchers.IO) {
        val zoneId = timeZonesRepo.getFiveOClockTimeZoneId()
        if (zoneId != 0) {
            val cocktails = regionalCocktailsDao.getAllCocktailsInZone(zoneId)
            DBResponse.Success(chooseCocktail(cocktails))
        } else {
           DBResponse.Error(R.string.water)
        }
    }

    private fun chooseCocktail(
        cocktails: List<DBCocktails>
    ): String = cocktails[(0 .. cocktails.size).rand()].name
}
