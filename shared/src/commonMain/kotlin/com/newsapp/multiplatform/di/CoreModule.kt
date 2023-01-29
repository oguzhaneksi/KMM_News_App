package com.newsapp.multiplatform.di

import com.newsapp.multiplatform.data.db.NewsDb
import com.newsapp.multiplatform.data.network.ApiService
import com.newsapp.multiplatform.data.repository.NewsRepositoryImpl
import com.newsapp.multiplatform.data.repository.datasource.NewsLocalDataSource
import com.newsapp.multiplatform.data.repository.datasource.implementation.NewsLocalDataSourceImpl
import com.newsapp.multiplatform.domain.repository.NewsRepository
import com.newsapp.multiplatform.domain.usecase.*
import com.newsapp.multiplatform.util.Constants.API_KEY
import com.newsapp.multiplatform.util.Constants.BASE_URL
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

fun coreModule(enableNetworkLogging: Boolean = false) = module {
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = URLProtocol.HTTPS }
                }
                headers {
                    append("x-api-key", API_KEY)
                }
            }

            if (enableNetworkLogging) {
                install(Logging) {
                    level = LogLevel.BODY
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.e(tag = "Http Client", message = message)
                        }
                    }
                }
            }

            install(ContentNegotiation) {
                json()
            }
        }
    }
    single { ApiService(get()) }
    single { NewsDb(get()) }
    single<NewsLocalDataSource> { NewsLocalDataSourceImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(apiService = get(), newsLocalDataSource = get()) }
    single { GetNewsSourcesUseCase(repository = get()) }
    single { GetArticlesUseCase(repository = get()) }
    single { SaveArticleUseCase(newsRepository = get()) }
    single { GetSavedArticlesUseCase(newsRepository = get()) }
    single { DeleteArticleUseCase(newsRepository = get()) }
}