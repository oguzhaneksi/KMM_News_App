package com.newsapp.multiplatform.android.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newsapp.multiplatform.android.ui.components.ArticleRow
import com.newsapp.multiplatform.android.ui.themes.titleColorDark
import com.newsapp.multiplatform.android.ui.themes.titleColorLight
import com.newsapp.multiplatform.android.ui.viewmodels.HomeViewModel
import com.newsapp.multiplatform.android.ui.viewmodels.SavedViewModel
import com.newsapp.multiplatform.domain.model.Article
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedScreen(
    navController: NavController,
    viewModel: SavedViewModel = koinViewModel()
) {
    val articles by viewModel.articles.collectAsState()
    val darkTheme = isSystemInDarkTheme()
    if (articles.isNotEmpty()) {
        SavedArticleList(articles = articles)
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "No saved article yet",
                color = if (darkTheme) titleColorDark else titleColorLight,
                fontSize = 21.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SavedArticleList(
    viewModel: SavedViewModel = koinViewModel(),
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
            val dismissState = rememberDismissState(initialValue = DismissValue.Default)
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.deleteArticle(it.url)
            }
            SwipeToDismiss(
                state = dismissState,
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.Red
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
            ) {
                ArticleRow(
                    article = it,
                    onClick = { article ->
                        // TODO
                    },
                    onSaveClick = {}
                )
            }
        }
    }
}