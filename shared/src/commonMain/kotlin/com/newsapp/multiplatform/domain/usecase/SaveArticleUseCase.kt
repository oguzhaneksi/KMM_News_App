package com.newsapp.multiplatform.domain.usecase

import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.repository.NewsRepository

class SaveArticleUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(article: Article) {
        newsRepository.saveArticle(article)
    }
}