package com.newsapp.multiplatform.domain.usecase

import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.repository.NewsRepository

class GetSavedArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): List<Article> = newsRepository.getSavedArticles()
}