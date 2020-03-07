package com.softaai.dkatalisassignment.repository

import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService

open class TrendingRepository(
    val api: TrendingRepositoryApiService,
    val dao: TrendingRepositoryDao
) {

    suspend fun getAllTrendingRepositories() = api.getTrendingRepositories()

    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository) {
        dao.insertAllTrendingRepositories(*trendingRepositories)
    }

    fun getAllTrendingRepositoriesList(): List<GithubRepository> {
        return dao.allTrendingRepositoryList
    }
}