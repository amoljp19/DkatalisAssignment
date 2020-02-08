package com.softaai.dkatalisassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrendingRepositoryDao {

    @get:Query("SELECT * FROM gitHubRepository")
    val allTrendingRepositoryList: List<GithubRepository>

    @Insert
    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository)
}