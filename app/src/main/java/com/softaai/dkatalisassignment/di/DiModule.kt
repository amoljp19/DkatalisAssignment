package com.softaai.dkatalisassignment.di

import androidx.room.Room
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import com.softaai.dkatalisassignment.utils.Constants
import com.softaai.dkatalisassignment.utils.SPUtils
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val mainActivityViewModelModule = module {
    viewModel {
        MainActivityViewModel(get(), get())
    }
}

val githubRepositoryViewModel = module {
    viewModel {
        GithubRepositoryViewModel()
    }
}

val trendingRepositoryModule = module {
    single {
        TrendingRepository(get(), get())
    }
}

val trendingRepositoryDbModule = module {
    single {
        Room.databaseBuilder(get(), TrendingRepositoryDatabase::class.java, "trending_repository_db").build()
    }

}

val trendingRepositoryDaoModule = module{
    single{
        get<TrendingRepositoryDatabase>().trendingRepositoryDao()
    }
}

val spUtilsModule = module {
    single {
        SPUtils(get())
    }
}

val apiModule = module {
    fun provideTrendingRepositoryApiService(retrofit: Retrofit): TrendingRepositoryApiService {
        return retrofit.create(TrendingRepositoryApiService::class.java)
    }

    single { provideTrendingRepositoryApiService(get()) }
}

val retrofitModule = module {

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