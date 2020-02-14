package com.softaai.dkatalisassignment

import com.softaai.dkatalisassignment.data.local.GithubRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TrendingRepositoryDaoTest : BaseTest() {

    override fun setUp() {
        super.setUp()
        initDataBase()
    }

    @Test
    fun getTrendingRepositoryData() {
        runBlocking {
            val trendingRepositories = database.trendingRepositoryDao().allTrendingRepositoryList
            Assert.assertEquals(1, trendingRepositories.size)
        }

    }

    private fun initDataBase() {
        runBlocking {
            val githubRepository = GithubRepository(id=0, author="twostraws", name="ControlRoom", avatar="https://github.com/twostraws.png", description="A macOS app to control the Xcode Simulator.", language="Swift", languageColor="#ffac45", stars="1245", forks="70", currentPeriodStars="440")
            //val list:List<GithubRepository> = listOf(githubRepository)
            database.trendingRepositoryDao().insertAllTrendingRepositories(*listOf(githubRepository).toTypedArray())
        }
    }
}