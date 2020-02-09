package com.softaai.dkatalisassignment

import android.app.Application
import com.softaai.dkatalisassignment.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class TrendingRepositoriesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TrendingRepositoriesApp)
            modules(listOf(mainActivityViewModelModule, githubRepositoryViewModel, trendingRepositoryModule, apiModule, retrofitModule))
        }
    }
}