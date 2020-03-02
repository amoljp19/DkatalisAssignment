package com.softaai.dkatalisassignment.di

import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules


@Category(CheckModuleTest::class)
class CheckDiModuleTest {

    // Due to context dependency injection problem in koin test
    // some test cases failed

    @Test
    fun checkMainActivityViewModelModule() =
        checkModules {
            modules(mainActivityViewModelModule)
        }

    @Test
    fun checkGithubRepositoryViewModelModule() =
        checkModules {
            modules(githubRepositoryViewModel)
        }

    @Test
    fun checkTrendingRepositoryModule() =
        checkModules {
            modules(trendingRepositoryModule)
        }

    @Test
    fun checkTrendingRepositoryDaoModule() =
        checkModules {
            modules(trendingRepositoryDaoModule)
        }

    @Test
    fun checkSharedPreferencesModule() =
        checkModules {
            modules(sharedPreferencesModule)
        }

    @Test
    fun checkApiModule() =
        checkModules {
            modules(apiModule)
        }


    @Test
    fun checkRetrofitModule() =
        checkModules {
            modules(retrofitModule)
        }

}