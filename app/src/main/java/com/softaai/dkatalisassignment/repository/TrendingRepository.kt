package com.softaai.dkatalisassignment.repository

import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService

class TrendingRepository(private val api: TrendingRepositoryApiService) {
    suspend fun getAllTrendingRepositories() = api.getTrendingRepositories()
}