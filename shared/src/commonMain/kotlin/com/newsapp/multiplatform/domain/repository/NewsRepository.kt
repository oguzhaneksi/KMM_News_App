package com.newsapp.multiplatform.domain.repository

import com.newsapp.multiplatform.data.model.SourceResponseDto
import com.newsapp.multiplatform.data.model.enums.NewsCategory
import com.newsapp.multiplatform.data.network.utils.NetworkResult
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.util.Constants
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getSources(
        category: NewsCategory? = null,
        language: String? = null,
        country: String? = null
    ): Flow<NetworkResult<SourceResponseDto>>

    suspend fun getTopHeadlines(
        country: String? = null,
        category: NewsCategory? = null,
        query: String? = null,
        page: Int = Constants.STARTING_PAGE_INDEX
    ): NetworkResult<List<Article>>

    suspend fun saveArticle(article: Article)

    suspend fun getSavedArticles(): List<Article>

    suspend fun deleteArticle(url: String)
}