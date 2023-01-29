package com.newsapp.multiplatform.domain.usecase

import com.newsapp.multiplatform.data.network.utils.NetworkResult
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.repository.NewsRepository
import com.newsapp.multiplatform.domain.state.ArticleState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetArticlesUseCase(private val repository: NewsRepository) {

    suspend fun execute(
        query: String? = null,
        countryCode: String
    ): ArticleState {
        return when (val result = repository.getTopHeadlines(query = query, country = countryCode)) {
            is NetworkResult.Success -> {
                ArticleState.Success(result.data ?: emptyList())
            }
            else -> {
                ArticleState.Failure(result.errorCode.toString(), result.errorMessage.toString())
            }
        }
    }

}