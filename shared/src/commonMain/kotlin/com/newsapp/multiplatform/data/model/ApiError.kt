package com.newsapp.multiplatform.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(

    @SerialName("status")
    val status: String?,

    @SerialName("code")
    val code: String?,

    @SerialName("message")
    val message: String?
)
