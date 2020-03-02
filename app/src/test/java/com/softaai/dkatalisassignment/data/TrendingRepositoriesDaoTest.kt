package com.softaai.dkatalisassignment.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softaai.dkatalisassignment.TrendingRepositoriesTestApp
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class TrendingRepositoriesDaoTest : KoinTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val trendingRepository = createTrendingRepository()
    val trendingRepositoryDao by inject<TrendingRepositoryDao>()
    val trendingRepositoryDatabase by inject<TrendingRepositoryDatabase>()

    @Before
    fun initDependencies() {
        startKoin{
            TrendingRepositoriesTestApp()
        }
    }

    @After
    fun tearDown() {
        trendingRepositoryDatabase.close()
        stopKoin()
    }

    @Test
    fun insertAndRetrieveTest() {
        trendingRepositoryDao.insertAllTrendingRepositories(trendingRepository)
        trendingRepositoryDao.allTrendingRepositoryList
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