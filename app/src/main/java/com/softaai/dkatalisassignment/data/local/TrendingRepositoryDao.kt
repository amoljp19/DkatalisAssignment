package com.softaai.dkatalisassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TrendingRepositoryDao {

    @get:Query("SELECT * FROM gitHubRepository")
    val allTrendingRepositoryList: LiveData<List<GithubRepository>>

    @Insert(onConflict = REPLACE)
    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository)
}