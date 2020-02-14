package com.softaai.dkatalisassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GithubRepositoryViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val githubRepositoryViewModel = GithubRepositoryViewModel()


    @Test
    fun testGithubRepositoryNotNull() {
        val githubRepository = GithubRepository(
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
        githubRepositoryViewModel.repository = githubRepository
        MatcherAssert.assertThat(githubRepositoryViewModel.repository, CoreMatchers.notNullValue())
    }
}