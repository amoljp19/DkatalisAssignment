package com.softaai.dkatalisassignment.repository

import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService


class TrendingRepository(
    private val api: TrendingRepositoryApiService,
    private val dao: TrendingRepositoryDao
) {

    suspend fun getAllTrendingRepositories() = api.getTrendingRepositories()

    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository) {
        dao.insertAllTrendingRepositories(*trendingRepositories)
    }

    fun getAllTrendingRepositoriesList(): List<GithubRepository> {
        return dao.allTrendingRepositoryList
    }
}