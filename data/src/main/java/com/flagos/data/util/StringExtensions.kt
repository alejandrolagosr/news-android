package com.flagos.data.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

fun String.toRelativeTime(): String {
    val minutes: Long
    val hours: Long
    val days: Long

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime = LocalDateTime.parse(this, formatter)
        val zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("UTC"))
        val now = ZonedDateTime.now(ZoneId.of("UTC"))

        minutes = ChronoUnit.MINUTES.between(zonedDateTime, now)
        hours = ChronoUnit.HOURS.between(zonedDateTime, now)
        days = ChronoUnit.DAYS.between(zonedDateTime, now)
    } else {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val date = formatter.parse(this) ?: return ""

        val now = Date()
        val diffInMillis = now.time - date.time
        minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
        hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
        days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
    }

    return when {
        minutes < 60 -> "${minutes}m"
        hours < 24 -> "${hours}h"
        else -> "${days}d"
    }
}