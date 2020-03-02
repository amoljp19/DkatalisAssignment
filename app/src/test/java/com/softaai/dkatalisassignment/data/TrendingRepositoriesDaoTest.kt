package com.softaai.dkatalisassignment.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.softaai.dkatalisassignment.TrendingRepositoriesApp
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class TrendingRepositoriesDaoTest : KoinTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val trendingRepository = createTrendingRepository()
    val trendingRepositoryDao by inject<TrendingRepositoryDao>()
    val trendingRepositoryDatabase by inject<TrendingRepositoryDatabase>()

    val trendingRepositoryDaoTestModule = module(override = true){
        single { TrendingRepositoriesApp() as Context }
        single {
            Room.inMemoryDatabaseBuilder(
                get(),
                TrendingRepositoryDatabase::class.java
            ).build()
        }
        single {
            get<TrendingRepositoryDatabase>().trendingRepositoryDao()
        }
    }

    @Before
    fun initDependencies() {
        startKoin{
            modules(trendingRepositoryDaoTestModule)
        }
    }

    @After
    fun tearDown() {
        trendingRepositoryDatabase.close()
        stopKoin()
    }

    @Test
    fun insertAndRetrieveTest() {
        Runnable {
            koinApplication {
                trendingRepositoryDao.insertAllTrendingRepositories(trendingRepository)
                trendingRepositoryDao.allTrendingRepositoryList
            }
        }
    }

    fun createTrendingRepository(): GithubRepository{
        return GithubRepository(
            id = 0,
            author = "twostraws",
            name = "ControlRoom",
            avatar = "https://github.com/twostraws.png",
            description = "A macOS app to control the Xcode Simulator.",
            language = "Swift",
            languageColor = "#ffac45",
            stars = "1245",
            forks = "70",
            currentPeriodStars = "440"
        )
    }
}