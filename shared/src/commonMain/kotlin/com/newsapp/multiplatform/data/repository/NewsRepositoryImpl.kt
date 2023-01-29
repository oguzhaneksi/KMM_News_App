package com.newsapp.multiplatform.data.repository

import com.newsapp.multiplatform.data.mapper.toArticle
import com.newsapp.multiplatform.data.model.NewsResponseDto
import com.newsapp.multiplatform.data.model.SourceResponseDto
import com.newsapp.multiplatform.data.model.enums.NewsCategory
import com.newsapp.multiplatform.data.network.ApiService
import com.newsapp.multiplatform.data.network.utils.NetworkResult
import com.newsapp.multiplatform.data.network.utils.safeApiCall
import com.newsapp.multiplatform.data.repository.datasource.NewsLocalDataSource
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.repository.NewsRepository
import io.ktor.client.call.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class NewsRepositoryImpl(
    private val apiService: ApiService,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getSources(category: NewsCategory?, language: String?, country: String?): Flow<NetworkResult<SourceResponseDto>> {
        val networkResponse =  safeApiCall {
            apiService.getSources(category, language, country).body() as SourceResponseDto
        }
        return flowOf(networkResponse)
    }

    override suspend fun getTopHeadlines(
        country: String?,
        category: NewsCategory?,
        query: String?,
        page: Int
    ): NetworkResult<List<Article>> {
        val networkResponse = safeApiCall {
            val body = apiService.getTopHeadlines(country, category, query, page = page).body() as NewsResponseDto
            body.articles.map { it.toArticle() }
        }
        return networkResponse
    }

    override suspend fun saveArticle(article: Article) {
        newsLocalDataSource.saveArticle(article)
    }

    override suspend fun getSavedArticles(): List<Article> = newsLocalDataSource.getArticles()

    override suspend fun deleteArticle(url: String) {
        newsLocalDataSource.deleteArticleByUrl(url)
    }

}