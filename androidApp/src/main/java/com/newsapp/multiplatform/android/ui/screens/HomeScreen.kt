package com.newsapp.multiplatform.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.newsapp.multiplatform.android.ui.components.ArticleRow
import com.newsapp.multiplatform.android.ui.components.SearchBox
import com.newsapp.multiplatform.android.ui.viewmodels.HomeViewModel
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.domain.state.ArticleState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    Column {
        val searchState = viewModel.searchBarState.collectAsState()
        val articleState by viewModel.articleState.collectAsState()
        SearchBox(state = searchState as MutableState<String>, onQueryTextChanged = {
            viewModel.onSearchQueryChanged(it)
        })
        when (articleState) {
            is ArticleState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is ArticleState.Success -> {
                ArticleList(articles = (articleState as ArticleState.Success).articles)
            }
            else -> {
                // TODO
            }
        }
    }
}

@Composable
private fun ArticleList(
    viewModel: HomeViewModel = koinViewModel(),
    articles: List<Article>
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 56.dp)
    ) {
        items(
            items = articles,
            key = {
                it.url
            }
        ) {
            ArticleRow(
                article = it,
                onClick = { article ->
                    // TODO
                },
                onSaveClick = { article ->
                    viewModel.saveArticle(article)
                },
                showDropdownMenu = true
            )
        }
    }
}