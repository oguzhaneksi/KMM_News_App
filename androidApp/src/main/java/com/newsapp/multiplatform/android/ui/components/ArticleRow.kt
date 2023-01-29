package com.newsapp.multiplatform.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.newsapp.multiplatform.android.ui.themes.*
import com.newsapp.multiplatform.domain.model.Article
import com.newsapp.multiplatform.util.DateOperations

@Composable
fun ArticleRow(
    article: Article,
    onClick: (Article) -> Unit,
    onSaveClick: (Article) -> Unit,
    showDropdownMenu: Boolean = false
) {
    val isDarkTheme = isSystemInDarkTheme()
    var showMenu by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(color = if (isDarkTheme) backgroundColorDark else backgroundColorLight)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 8.dp)
            .clickable {
                onClick(article)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f),
            shape = RoundedCornerShape(corner = CornerSize(12.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = article.urlToImage,
                contentDescription = article.title
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(.93f),
                    text = article.title,
                    color = if (isDarkTheme) titleColorDark else titleColorLight,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                if (showDropdownMenu) {
                    Box {
                        IconButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                showMenu = !showMenu
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = if (isDarkTheme) titleColorDark else titleColorLight
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = {
                                onSaveClick(article)
                                showMenu = false
                            }) {
                                Text(text = "Save")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${article.author} - ${DateOperations.parse(article.publishedAt, "yyyy-MM-dd'T'HH:mm:ss'Z'", "dd.MM.yyyy")}",
                color = if (isDarkTheme) descriptionColorDark else descriptionColorLight,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun ArticleRowPreview() {
    ArticleRow(
        article = Article(
            author = "Josh Alper",
            content = "It didnt take long for Eagles quarterback Jalen Hurts to set the tone for Saturday nights game against the Giants. Hurts his wide receiver DeVonta Smith for 40 yards on the second snap of the game a… [+1265 chars]",
            title = "Nick Sirianni on Jalen Hurts: It’s like having Michael Jordan out there - NBC Sports",
            description = "It didn’t take long for Eagles quarterback Jalen Hurts to set the tone for Saturday night’s game against the Giants.Hurts his wide receiver DeVonta Smith for 40 yards on the second snap of the game and the Eagles were up 7-0 thanks to a touchdown pass to tigh…",
            url = "https://profootballtalk.nbcsports.com/2023/01/22/nick-sirianni-on-jalen-hurts-its-like-having-michael-jordan-out-there/",
            urlToImage = "https://profootballtalk.nbcsports.com/wp-content/uploads/sites/25/2023/01/GettyImages-1246436228-e1674392435281.jpg",
            publishedAt = "2023-01-22T13:00:00Z"
        ),
        onClick = {},
        onSaveClick = {}
    )
}