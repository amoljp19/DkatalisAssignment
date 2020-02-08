package com.softaai.dkatalisassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrendingRepositoryDao {

    @get:Query("SELECT * FROM trendingRepository")
    val allTrendingRepositoryList: List<TrendingRepository>

    @Insert
    fun insertAllTrendingRepositories(vararg trendingRepositories: TrendingRepository)
}