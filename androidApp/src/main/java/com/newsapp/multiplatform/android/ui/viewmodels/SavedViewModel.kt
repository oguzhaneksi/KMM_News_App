package com.newsapp.multiplatform.android.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.usecase.DeleteArticleUseCase
import com.newsapp.multiplatform.domain.usecase.GetSavedArticlesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
): ViewModel() {

    private var _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedArticles()
        }
    }

    private suspend fun getSavedArticles() {
        _articles.update {
            getSavedArticlesUseCase.execute()
        }
    }

    fun deleteArticle(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteArticleUseCase.execute(url)
            getSavedArticles()
        }
    }

}