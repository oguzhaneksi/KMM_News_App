package com.newsapp.multiplatform.data.mapper

import com.newsapp.multiplatform.data.model.ArticleDto
import com.newsapp.multiplatform.domain.model.Article
import database.ArticleEntity

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = url_to_image
    )
}