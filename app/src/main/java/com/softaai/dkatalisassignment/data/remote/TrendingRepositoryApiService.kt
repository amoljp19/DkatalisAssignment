package com.softaai.dkatalisassignment.data.remote

import com.softaai.dkatalisassignment.data.local.GithubRepository
import retrofit2.Response
import retrofit2.http.GET

interface TrendingRepositoryApiService {

    @GET("/repositories")
    suspend fun getTrendingRepositories(): Response<List<GithubRepository>>
}