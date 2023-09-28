package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBPlaces
import com.wreckingballsoftware.fiveoclocksomewhere.database.PlacesDao
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlacesRepo(
    private val timeZonesRepo: TimeZonesRepo,
    private val placesDao: PlacesDao,
) {
    suspend fun getPlaceWhereIts5OClock(): Response<DBPlaces> = withContext(Dispatchers.IO) {
        val zones = timeZonesRepo.getFiveOClockTimeZones()
        zones.ifEmpty {
            Response.Error<String>(errorMessageId = R.string.country_error)
        }

        val places = placesDao.getAllPlacesInZones(zones.map { it.id })
        places.ifEmpty {
            Response.Error<String>(errorMessageId = R.string.country_error)
        }

        Response.Success(choosePlace(places))
    }

    private fun choosePlace(
        places: List<DBPlaces>
    ): DBPlaces = places[(0 .. places.size).rand()]
}