package com.softaai.dkatalisassignment.di

import android.content.Context
import androidx.room.Room
import com.softaai.dkatalisassignment.TrendingRepositoriesApp
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import com.softaai.dkatalisassignment.utils.Constants
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainActivityViewModelTestModule = module(override = true){

    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    fun provideTrendingRepositoryApiService(retrofit: Retrofit): TrendingRepositoryApiService {
        return retrofit.create(TrendingRepositoryApiService::class.java)
    }

    single { TrendingRepositoriesApp() as Context }
    single {
        Room.databaseBuilder(
            get(),
            TrendingRepositoryDatabase::class.java,
            "trending_repository_db"
        ).build()
    }
    single { get<TrendingRepositoryDatabase>().trendingRepositoryDao() }
    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideTrendingRepositoryApiService(get()) }
    single { TrendingRepository(get(), get()) }
    single { MainActivityViewModel(get()) }
}


val githubRepositoryViewModelTestModule = module(override = true){
    viewModel {
        GithubRepositoryViewModel()
    }
}

val trendingRepositoryTestModule = module(override = true){

    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    fun provideTrendingRepositoryApiService(retrofit: Retrofit): TrendingRepositoryApiService {
        return retrofit.create(TrendingRepositoryApiService::class.java)
    }

    single { TrendingRepositoriesApp() as Context }
    single {
        Room.databaseBuilder(
            get(),
            TrendingRepositoryDatabase::class.java,
            "trending_repository_db"
        ).build()
    }

    single { get<TrendingRepositoryDatabase>().trendingRepositoryDao() }
    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideTrendingRepositoryApiService(get()) }
    single {
        TrendingRepository(get(), get())
    }
}

val trendingRepositoryDbTestModule = module(override = true) {

    single { TrendingRepositoriesApp() as Context }
    single {
        Room.databaseBuilder(
            get(),
            TrendingRepositoryDatabase::class.java,
            "trending_repository_db"
        ).build()
    }

}

val trendingRepositoryDaoTestModule = module(override = true){
    single { TrendingRepositoriesApp() as Context }
    single {
        Room.databaseBuilder(
            get(),
            TrendingRepositoryDatabase::class.java,
            "trending_repository_db"
        ).build()
    }
    single {
        get<TrendingRepositoryDatabase>().trendingRepositoryDao()
    }
}

val apiTestModule = module(override = true){
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    fun provideTrendingRepositoryApiService(retrofit: Retrofit): TrendingRepositoryApiService {
        return retrofit.create(TrendingRepositoryApiService::class.java)
    }

    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideTrendingRepositoryApiService(get()) }
}

val retrofitTestModule = module {

    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}