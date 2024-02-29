package com.example.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toFormattedTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val localDateTime = LocalDateTime.parse(this, formatter)
    val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return localDateTime.format(timeFormatter)
}
