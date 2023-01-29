package com.newsapp.multiplatform.data.repository.datasource.implementation

import com.newsapp.multiplatform.data.db.NewsDb
import com.newsapp.multiplatform.data.mapper.toArticle
import com.newsapp.multiplatform.data.repository.datasource.NewsLocalDataSource
import com.newsapp.multiplatform.domain.model.Article

class NewsLocalDataSourceImpl(db: NewsDb): NewsLocalDataSource {

    private val queries = db.newsQueries

    override suspend fun saveArticle(article: Article) {
        queries.insertArticle(
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            title = article.title,
            url = article.url,
            url_to_image = article.urlToImage
        )
    }

    override suspend fun getArticleByUrl(url: String): Article? = queries.getArticleByUrl(url).executeAsOneOrNull()?.toArticle()

    override suspend fun getArticles(): List<Article> = queries.getAllArticles().executeAsList().map { it.toArticle() }

    override suspend fun deleteArticleByUrl(url: String) {
        queries.deleteArticleByUrl(url)
    }
}