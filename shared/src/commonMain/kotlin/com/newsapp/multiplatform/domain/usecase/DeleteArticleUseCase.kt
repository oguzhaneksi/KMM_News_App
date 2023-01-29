package com.newsapp.multiplatform.domain.usecase

import com.newsapp.multiplatform.domain.repository.NewsRepository

class DeleteArticleUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(url: String) = newsRepository.deleteArticle(url)
}