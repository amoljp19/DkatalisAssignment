package com.softaai.dkatalisassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import retrofit2.Response

@Dao
interface TrendingRepositoryDao {

    @get:Query("SELECT * FROM GitHubRepository")
    val allTrendingRepositoryList: List<GithubRepository>

    //@Insert(onConflict = REPLACE)
    @Insert
    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository)

    //@Insert(onConflict = REPLACE)
    @Insert
    fun saveAllTrendingRepositories(trendingRepositories: List<GithubRepository>?)
}