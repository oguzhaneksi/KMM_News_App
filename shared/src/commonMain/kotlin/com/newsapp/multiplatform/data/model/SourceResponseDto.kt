package com.newsapp.multiplatform.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponseDto(
    @SerialName("sources")
    val sources: List<SourceXDto>,
    @SerialName("status")
    val status: String
)