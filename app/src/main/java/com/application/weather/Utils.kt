package com.application.weather

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    fun getCurrentTimeIn24HourFormat(): Int {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formattedTime = currentTime.format(formatter)
        return formattedTime.toInt()
    }

    fun isDaytime(): Boolean {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formattedTime = currentTime.format(formatter)
        val currentHour = formattedTime.toInt()

        // Assuming day hours are from 6 AM to 6 PM (18:00)
        val isDaytime = currentHour in 6..17
        return isDaytime
    }

    fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()

        // Get the day of the week
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayString = when (dayOfWeek) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Unknown"
        }

        // Get the day of the month
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        // Get the month
        val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)

        // Format the result as "DayString, MonthString DayInt"
        val result = "$dayString, $month $dayOfMonth"
        return result
    }
}