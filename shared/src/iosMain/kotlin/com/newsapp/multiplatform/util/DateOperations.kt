package com.newsapp.multiplatform.util

import platform.Foundation.NSDateFormatter

actual class DateOperations {
    actual companion object {
        actual fun parse(dateTime: String, currentPattern: String, newPattern: String): String {
            return try {
                val dateFormatterGet = NSDateFormatter()
                dateFormatterGet.dateFormat = currentPattern
                val dateFormatterOut = NSDateFormatter()
                dateFormatterOut.dateFormat = newPattern
                val inputDate = dateFormatterGet.dateFromString(dateTime)
                inputDate?.let { dateFormatterOut.stringFromDate(it) } ?: dateTime
            } catch (e: Exception) {
                e.printStackTrace()
                dateTime
            }
        }
    }

}