package com.newsapp.multiplatform.di

import com.newsapp.multiplatform.data.db.DatabaseDriverFactory
import io.ktor.client.engine.okhttp.*
import org.koin.dsl.module

actual val platformModule = module {
    single { OkHttp.create() }
    single { DatabaseDriverFactory(get()).createDriver() }
}