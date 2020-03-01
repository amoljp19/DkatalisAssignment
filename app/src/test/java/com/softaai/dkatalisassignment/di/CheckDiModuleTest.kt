package com.softaai.dkatalisassignment.di

import android.content.Context
import com.softaai.dkatalisassignment.TrendingRepositoriesApp
import io.kotlintest.runner.jvm.spec.SingleInstanceSpecRunner
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.Mockito.mock


@Category(CheckModuleTest::class)
class CheckDiModuleTest {

    @Test
    fun checkDiModule() =
        checkModules {
            modules(diModule)
        }

//    @Test
//    fun checkMainActivityViewModelModule() =
//        checkModules {
//            modules(mainActivityViewModelModule)
//        }
//
//    @Test
//    fun checkGithubRepositoryViewModelModule() =
//        checkModules {
//            modules(githubRepositoryViewModel)
//        }
//
//    @Test
//    fun checkTrendingRepositoryModule() =
//        checkModules {
//            modules(trendingRepositoryModule)
//        }
//
//    @Test
//    fun checkTrendingRepositoryDaoModule() =
//        checkModules {
//            modules(trendingRepositoryDaoModule)
//        }
//
//    @Test
//    fun checkSpUtilsModule() =
//        checkModules {
//            modules(spUtilsModule)
//        }
//
//    @Test
//    fun checkApiModule() =
//        checkModules {
//            modules(apiModule)
//        }


//    @Test
//    fun checkRetrofitModule() =
//        checkModules {
//            modules(retrofitModule)
//        }

}