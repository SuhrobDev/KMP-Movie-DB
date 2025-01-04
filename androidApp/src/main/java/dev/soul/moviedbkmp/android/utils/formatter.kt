package dev.soul.moviedbkmp.android.utils

import java.text.DecimalFormat

fun formatNumber(value: Double): String {
    val formatter = DecimalFormat("#.#") // One decimal place or none if it's zero
    return formatter.format(value)
}

fun formatMinutesToHoursAndMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return if (hours > 0) {
        if (remainingMinutes > 0) "${hours}h ${remainingMinutes}m" else "${hours}h"
    } else {
        "${remainingMinutes}m"
    }
}