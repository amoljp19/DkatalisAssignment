package com.softaai.dkatalisassignment.di

import android.app.AppComponentFactory
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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


//val diModule = module {

    //
//    ////////// viewmodel ///////////////////
//
//    single {MainActivityViewModel(get(), get())}
//    single {GithubRepositoryViewModel()}
//
//
//    /////////////// db ////////////////////////////
//
//    fun provideSharedPreferences(context: Context): SharedPreferences{
//        val PREFS_NAME = "githubtrend"
//        val sharedPref: SharedPreferences =
//            context.getSharedPreferences(PREFS_NAME, 0x0000)
//        return sharedPref
//    }
//
////    fun provideContext(): Context{
////        return TrendingRepositoriesApp()
////    }
//
////    single { provideContext() }
//
//
//    single{provideSharedPreferences(get<Context>().applicationContext)}
//
////    single {
////        SPUtils(get())
////    }
//
//    single {
//        Room.databaseBuilder(
//            get(),
//            TrendingRepositoryDatabase::class.java,
//            "trending_repository_db"
//        ).build()
//    }
//
//    single {
//        TrendingRepository(get(), get())
//    }
//
//
//
//    single {
//        get<TrendingRepositoryDatabase>().trendingRepositoryDao()
//    }
//
//
//
//    //////////// retrofit ////////////////
//
//    fun provideMoshi(): Moshi {
//        return Moshi.Builder().build()
//    }
//
//    fun provideHttpClient(): OkHttpClient {
//        val okHttpClientBuilder = OkHttpClient.Builder()
//
//        return okHttpClientBuilder.build()
//    }
//
//    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constants.URL)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .client(client)
//            .build()
//    }
//
//    fun provideTrendingRepositoryApiService(retrofit: Retrofit): TrendingRepositoryApiService {
//        return retrofit.create(TrendingRepositoryApiService::class.java)
//    }
//
//    single { provideMoshi() }
//    single { provideHttpClient() }
//    single { provideRetrofit(get(), get()) }
//    single { provideTrendingRepositoryApiService(get()) }
//}


    //////////////////////////////////////****************************////////////////////////


    val mainActivityViewModelModule = module(override = true){
        //    viewModel {
//        MainActivityViewModel(get(), get())
//    }

        single { MainActivityViewModel(get(), get()) }
    }

    val sharedPreferencesModule = module(override=true) {
        fun provideContext() : Context{
            return TrendingRepositoriesApp().applicationContext
        }

        single { TrendingRepositoriesApp().applicationContext as Context }

        fun provideSharedPreferences(context: Context): SharedPreferences {
            val PREFS_NAME = "githubtrend"
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(PREFS_NAME, 0x0000)
            return sharedPref
        }

        //single { provideContext() }
        single { TrendingRepositoriesApp().applicationContext as Context }
        single { provideSharedPreferences(get()) }
    }

    val githubRepositoryViewModel = module(override = true){
        viewModel {
            GithubRepositoryViewModel()
        }
    }

    val trendingRepositoryModule = module(override = true){
        single {
            TrendingRepository(get(), get())
        }
    }

    val trendingRepositoryDbModule = module(override = true) {

        single { TrendingRepositoriesApp().applicationContext as Context }
        single {
            Room.databaseBuilder(
                get(),
                TrendingRepositoryDatabase::class.java,
                "trending_repository_db"
            ).build()
        }

    }

    val trendingRepositoryDaoModule = module(override = true){
        single {
            get<TrendingRepositoryDatabase>().trendingRepositoryDao()
        }
    }

    val apiModule = module {
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