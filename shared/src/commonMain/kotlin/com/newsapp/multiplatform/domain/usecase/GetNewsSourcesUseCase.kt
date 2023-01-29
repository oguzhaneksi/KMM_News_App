package com.newsapp.multiplatform.domain.usecase

import com.newsapp.multiplatform.data.model.SourceResponseDto
import com.newsapp.multiplatform.data.network.utils.NetworkResult
import com.newsapp.multiplatform.domain.repository.NewsRepository
import com.newsapp.multiplatform.util.deviceLanguage
import kotlinx.coroutines.flow.Flow

class GetNewsSourcesUseCase(private val repository: NewsRepository) {

    suspend fun execute(): Flow<NetworkResult<SourceResponseDto>> {
        return repository.getSources(language = deviceLanguage)
    }
}