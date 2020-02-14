package com.softaai.dkatalisassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.remote.LoadingState
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService
import com.softaai.dkatalisassignment.utils.SPUtils
import java.util.concurrent.TimeUnit


class TrendingRepository(private val api: TrendingRepositoryApiService, private val dao: TrendingRepositoryDao) {

    suspend fun getAllTrendingRepositories() = api.getTrendingRepositories()

    fun saveTrendingRepositories(trendingRepositories: List<GithubRepository>?) {
        dao.saveAllTrendingRepositories(trendingRepositories)
    }

    fun insertAllTrendingRepositories(vararg trendingRepositories: GithubRepository){
        dao.insertAllTrendingRepositories(*trendingRepositories)
    }

    fun getAllTrendingRepositoriesList(): List<GithubRepository>{
        val list = dao.allTrendingRepositoryList
        return list
    }

//    suspend fun getAllTrendingRepositories1() : LiveData<List<GithubRepository>>{
//
//        refreshTrendingRepositories()
//
//        return dao.allTrendingRepositoryList
//    }

    private suspend fun refreshTrendingRepositories(){
        val response = api.getTrendingRepositories()
        if(response.isSuccessful){
            dao.saveAllTrendingRepositories(response.body())
        }
    }
}