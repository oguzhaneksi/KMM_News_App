package com.newsapp.multiplatform.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceXDto(
    @SerialName("category")
    val category: String,
    @SerialName("country")
    val country: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: String,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)