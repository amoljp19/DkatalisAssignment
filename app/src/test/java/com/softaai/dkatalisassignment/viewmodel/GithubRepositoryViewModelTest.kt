package com.softaai.dkatalisassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.di.githubRepositoryViewModelTestModule
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations


class GithubRepositoryViewModelTest: KoinTest{

    private val githubRepositoryViewModel: GithubRepositoryViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this@GithubRepositoryViewModelTest)
        startKoin{
            modules(githubRepositoryViewModelTestModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGithubRepositoryNotNull() {
        githubRepositoryViewModel.repository = getMockGithubRepository()
        MatcherAssert.assertThat(githubRepositoryViewModel.repository, CoreMatchers.notNullValue())
    }

    @Test
    fun testBind(){
        githubRepositoryViewModel.repository = getMockGithubRepository()
        Assert.assertEquals("#ffac45", githubRepositoryViewModel.repository?.languageColor)
        Assert.assertEquals(false, githubRepositoryViewModel.isExpanded)
    }

    fun getMockGithubRepository():GithubRepository{
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