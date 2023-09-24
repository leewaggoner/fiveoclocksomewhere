package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.database.TimeZonesDao
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class TimeZonesRepo(
    private val timeZonesDao: TimeZonesDao,
) {
    fun getFiveOClockTimeZoneId(): Int {
        val zoneString = findUTCZoneBetween5and6()
        return if (zoneString.isNotEmpty()) {
            timeZonesDao.getTimeZoneId(zoneString)
        } else {
            0
        }
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
}