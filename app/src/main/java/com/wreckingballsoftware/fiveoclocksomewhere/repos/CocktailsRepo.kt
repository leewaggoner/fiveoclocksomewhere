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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.random.Random

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

    suspend fun getPlaceWhereIts5OClock(): String = withContext(Dispatchers.IO) {
        val zoneString = findUTCZoneBetween5and6()
        if (zoneString.isNotEmpty()) {
            val zoneId = timeZonesDao.getTimeZoneId(zoneString)
            val places = countriesDao.getAllCountriesInZone(zoneId)
            pickACountry(places)
        } else {
            "Nowhere. Damn it!"
        }
    }


    suspend fun getAllCocktailsInZone(timeZone: Int): List<DBCocktails> = withContext(Dispatchers.IO) {
        regionalCocktailsDao.getAllCocktailsInZone(timeZone)
    }

    private fun findUTCZoneBetween5and6(): String {
        for (id in TimeZone.getAvailableIDs()) {
            val timeZone = TimeZone.getTimeZone(id)
            val zoneCalendar = Calendar.getInstance(timeZone)
            val hourOfDay = zoneCalendar.get(Calendar.HOUR_OF_DAY)

            if (hourOfDay == 17) {
                val sdf = SimpleDateFormat("XX", Locale.US)
                sdf.timeZone = TimeZone.getTimeZone(timeZone.id)
                val utcTime = sdf.format(zoneCalendar.time)
                val utcInt = utcTime.toInt()
                val tzSupported = utcTime.takeLast(2).toInt() == 0
                if (utcInt in -1200 .. 1200 && tzSupported) {
                    return utcTime
                }
            }
        }
        return ""
    }

    private fun pickACountry(places: List<DBCountries>): String {
        val rand = Random(System.currentTimeMillis()).nextFloat()
        return ""
    }
}
