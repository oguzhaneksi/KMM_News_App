package com.newsapp.multiplatform.data.repository.datasource

import com.newsapp.multiplatform.domain.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticle(article: Article)
    suspend fun getArticleByUrl(url: String): Article?
    suspend fun getArticles(): List<Article>
    suspend fun deleteArticleByUrl(url: String)
}