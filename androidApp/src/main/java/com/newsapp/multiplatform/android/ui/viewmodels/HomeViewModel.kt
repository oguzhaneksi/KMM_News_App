package com.newsapp.multiplatform.android.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.state.ArticleState
import com.newsapp.multiplatform.domain.usecase.GetArticlesUseCase
import com.newsapp.multiplatform.domain.usecase.SaveArticleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*


class HomeViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val saveArticleUseCase: SaveArticleUseCase
) : ViewModel() {

    private var _searchBarState = MutableStateFlow("")
    val searchBarState = _searchBarState.asStateFlow()

    val articleState = _searchBarState.mapLatest {
        getArticlesUseCase.execute(query = it, countryCode = Locale.getDefault().country)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ArticleState.Loading
    )

    fun onSearchQueryChanged(query: String) {
        _searchBarState.update {
            query
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            saveArticleUseCase.execute(article)
        }
    }
}