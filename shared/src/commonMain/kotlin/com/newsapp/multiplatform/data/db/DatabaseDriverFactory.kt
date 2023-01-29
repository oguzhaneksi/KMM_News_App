package com.newsapp.multiplatform.data.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

internal const val dbName = "news.db"