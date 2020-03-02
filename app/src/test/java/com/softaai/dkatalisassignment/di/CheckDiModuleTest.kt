package com.softaai.dkatalisassignment.di

import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules


@Category(CheckModuleTest::class)
class CheckDiModuleTest {

    @Test
    fun checkMainActivityViewModelModule() =
        checkModules {
            modules(mainActivityViewModelTestModule)
        }

    @Test
    fun checkGithubRepositoryViewModelModule() =
        checkModules {
            modules(githubRepositoryViewModelTestModule)
        }

    @Test
    fun checkTrendingRepositoryModule() =
        checkModules {
            modules(trendingRepositoryTestModule)
        }

    @Test
    fun checkTrendingRepositoryDaoModule() =
        checkModules {
            modules(trendingRepositoryDaoTestModule)
        }


    @Test
    fun checkApiModule() =
        checkModules {
            modules(apiTestModule)
        }


    @Test
    fun checkRetrofitModule() =
        checkModules {
            modules(retrofitTestModule)
        }

}