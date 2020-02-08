package com.softaai.dkatalisassignment.di

import com.softaai.dkatalisassignment.data.remote.TrendingReposApiService
import com.softaai.dkatalisassignment.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    fun provideTrendingReposApiService(retrofit: Retrofit): TrendingReposApiService {
        return retrofit.create(TrendingReposApiService::class.java)
    }

    single { provideTrendingReposApiService(get()) }
}

val retrofitModule = module {

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}