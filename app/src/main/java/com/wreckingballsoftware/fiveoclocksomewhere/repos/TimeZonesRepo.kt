package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.database.DBTimeZones
import com.wreckingballsoftware.fiveoclocksomewhere.database.TimeZonesDao
import java.util.Calendar
import java.util.TimeZone

class TimeZonesRepo(
    private val timeZonesDao: TimeZonesDao,
) {
    private var allZones = listOf<DBTimeZones>()

    fun getFiveOClockTimeZones(): List<DBTimeZones> {
        allZones.ifEmpty {
            allZones = timeZonesDao.getAllTimeZones()
        }
        return findUTCZoneBetween5and6(allZones)
    }

    private fun findUTCZoneBetween5and6(timeZones: List<DBTimeZones>): List<DBTimeZones> {
        val zones = mutableListOf<DBTimeZones>()
        for (id in timeZones) {
            val timeZone = TimeZone.getTimeZone(id.zone)
            val zoneCalendar = Calendar.getInstance(timeZone)
            val hourOfDay = zoneCalendar.get(Calendar.HOUR_OF_DAY)

            if (hourOfDay == 17) {
                zones.add(id)
            }
        }
        return zones
    }
}