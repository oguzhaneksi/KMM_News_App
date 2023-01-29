package com.newsapp.multiplatform.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    @SerialName("articles")
    val articles: List<ArticleDto>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)