package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.database.CountriesDao
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCountries
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepo(
    private val timeZonesRepo: TimeZonesRepo,
    private val countriesDao: CountriesDao,
) {
    suspend fun getPlaceWhereIts5OClock(): Response<String> = withContext(Dispatchers.IO) {
        val zones = timeZonesRepo.getFiveOClockTimeZones()
        zones.ifEmpty {
            Response.Error<String>(errorMessageId = R.string.country_error)
        }

        val places = countriesDao.getAllCountriesInZones(zones.map { it.id })
        places.ifEmpty {
            Response.Error<String>(errorMessageId = R.string.country_error)
        }

        Response.Success(choosePlace(places))
    }

    private fun choosePlace(
        places: List<DBCountries>
    ): String = places[(0 .. places.size).rand()].name
}