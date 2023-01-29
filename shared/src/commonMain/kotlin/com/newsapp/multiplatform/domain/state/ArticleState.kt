package com.newsapp.multiplatform.domain.state

import com.newsapp.multiplatform.domain.model.Article

sealed class ArticleState {
    object Loading: ArticleState()
    data class Success(val articles: List<Article>): ArticleState()
    data class Failure(val errorCode: String, val errorMessage: String): ArticleState()
}
