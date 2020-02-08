package com.softaai.dkatalisassignment.data.remote

import com.softaai.dkatalisassignment.data.model.TrendingReposResponse
import retrofit2.Response
import retrofit2.http.GET

interface TrendingReposApiService {

    @GET("/repositories")
    suspend fun getRepositories(): Response<List<TrendingReposResponse>>
}