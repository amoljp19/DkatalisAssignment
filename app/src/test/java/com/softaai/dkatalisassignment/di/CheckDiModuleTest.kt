package com.softaai.dkatalisassignment.di

import android.content.Context
import com.softaai.dkatalisassignment.TrendingRepositoriesApp
import io.kotlintest.runner.jvm.spec.SingleInstanceSpecRunner
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.Mockito.mock



@Category(CheckModuleTest::class)
class CheckDiModuleTest : KoinTest {

//    @Test
//    fun checkDiModule() =
//        checkModules {
//            modules(diModule)
//        }


//    @Test
//    fun `check all dependencies`() {
//        koinApplication {
//            modules(diModule)
//        }.checkModules()
//    }



    @Test
    fun checkMainActivityViewModelModule() {
//        =
//        checkModules {
//            modules(mainActivityViewModelModule)
//        }

        koinApplication {
            modules(mainActivityViewModelModule)
        }.checkModules()
    }

    @Test
    fun checkGithubRepositoryViewModelModule() {
//        =
//        checkModules {
//            modules(githubRepositoryViewModel)
//        }

        koinApplication {
            modules(githubRepositoryViewModel)
        }.checkModules()
    }

    @Test
    fun checkTrendingRepositoryModule() {
//        =
//        checkModules {
//            modules(trendingRepositoryModule)
//        }

        koinApplication {
            modules(trendingRepositoryModule)
        }.checkModules()
    }

    @Test
    fun checkTrendingRepositoryDaoModule() {
//        =
//        checkModules {
//            modules(trendingRepositoryDaoModule)
//        }

        koinApplication {
            modules(trendingRepositoryDaoModule)
        }.checkModules()
    }

    @Test
    fun checkSharedPreferencesModule() {
//        =
//        checkModules {
//            modules(sharedPreferencesModule)
//        }

        koinApplication {
            modules(sharedPreferencesModule)
        }.checkModules()
    }

    @Test
    fun checkApiModule() {
//        =
//        checkModules {
//            modules(apiModule)
//        }

        koinApplication {
            modules(apiModule)
        }.checkModules()
    }


    @Test
    fun checkRetrofitModule() {
//        =
//        checkModules {
//            modules(retrofitModule)
//        }

        koinApplication {
            modules(retrofitModule)
        }.checkModules()
    }

}