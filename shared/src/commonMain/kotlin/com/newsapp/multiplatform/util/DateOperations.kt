package com.newsapp.multiplatform.util

expect class DateOperations {
    companion object {
        fun parse(dateTime: String, currentPattern: String, newPattern: String): String
    }
}