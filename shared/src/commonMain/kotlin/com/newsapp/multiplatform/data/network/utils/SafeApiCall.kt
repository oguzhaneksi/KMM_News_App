package com.newsapp.multiplatform.data.network.utils


import com.newsapp.multiplatform.data.model.ApiError
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import kotlinx.serialization.decodeFromString

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(data = apiCall.invoke())
    } catch (e: RedirectResponseException) { // 3xx errors
        val networkError = getError(responseContent = e.response.bodyAsChannel())

        NetworkResult.Error(
            errorCode = networkError.code ?: e.response.status.value.toString(),
            errorMessage = networkError.message ?: e.message
        )
    } catch (e: ClientRequestException) { // 4xx errors
        val networkError = getError(responseContent = e.response.bodyAsChannel())

        NetworkResult.Error(
            errorCode = networkError.code ?: e.response.status.value.toString(),
            errorMessage = networkError.message ?: e.message
        )
    } catch (e: ServerResponseException) { // 5xx errors
        val networkError = getError(responseContent = e.response.bodyAsChannel())

        NetworkResult.Error(
            errorCode = networkError.code ?: e.response.status.value.toString(),
            errorMessage = networkError.message ?: e.message
        )
    } catch (e: Exception) {
        NetworkResult.Error(
            errorCode = "0",
            errorMessage = e.message ?: "An unknown error occurred"
        )
    }
}

fun getError(responseContent: ByteReadChannel): ApiError {
    return kotlinx.serialization.json.Json.decodeFromString(string = responseContent.toString())
    // throw IllegalArgumentException("Not a parsable error")
}
