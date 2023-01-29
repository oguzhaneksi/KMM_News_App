package com.newsapp.multiplatform.data.network

import com.newsapp.multiplatform.data.model.enums.NewsCategory
import com.newsapp.multiplatform.util.Constants.PAGING_SIZE
import com.newsapp.multiplatform.util.Constants.STARTING_PAGE_INDEX
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiService(private val httpClient: HttpClient){
    suspend fun getTopHeadlines(
        country: String? = null,
        category: NewsCategory? = null,
        query: String? = null,
        pageSize: Int = PAGING_SIZE,
        page: Int = STARTING_PAGE_INDEX
    ): HttpResponse{
        return httpClient.get(urlString = "v2/top-headlines") {
            url {
                country?.let { it1 -> parameters.append("country", it1) }
                category?.let { it1 -> parameters.append("category", it1.name) }
                query?.let { it1 -> parameters.append("q", it1) }
                parameters.append("pageSize", pageSize.toString())
                parameters.append("page", page.toString())
            }
        }
    }

    suspend fun getSources(
        category: NewsCategory? = null,
        language: String? = null,
        country: String? = null
    ): HttpResponse {
        return httpClient.get(urlString = "v2/top-headlines/sources") {
            url {
                category?.let { it1 -> parameters.append("category", it1.name) }
                language?.let { it1 -> parameters.append("language", it1) }
                country?.let { it1 -> parameters.append("country", it1) }
            }
        }
    }
}