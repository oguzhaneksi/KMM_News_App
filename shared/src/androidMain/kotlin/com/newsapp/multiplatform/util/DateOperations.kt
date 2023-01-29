package com.newsapp.multiplatform.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

actual class DateOperations {
    actual companion object {
        actual fun parse(dateTime: String, currentPattern: String, newPattern: String): String {
            return try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(currentPattern)).format(
                        DateTimeFormatter.ofPattern(newPattern))
                } else {
                    val simpleDateFormat = SimpleDateFormat(currentPattern)
                    val date = simpleDateFormat.parse(dateTime)
                    val newSimpleDateFormat = SimpleDateFormat(newPattern)
                    date?.let { newSimpleDateFormat.format(it) } ?: dateTime
                }
            } catch (e: Exception) {
                e.printStackTrace()
                dateTime
            }
        }
    }
}