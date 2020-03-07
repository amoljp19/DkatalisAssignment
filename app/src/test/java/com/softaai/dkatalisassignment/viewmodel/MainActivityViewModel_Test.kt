package com.softaai.dkatalisassignment.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.remote.LoadingState
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class MainActivityViewModel_Test{

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var _loadingState: LiveData<LoadingState>

    private lateinit var _data: LiveData<List<GithubRepository>>

    private val trendingRepositoryList = listOf(
        GithubRepository(
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
    ,
        GithubRepository(
            id = 1,
            author = "twostraws1",
            name = "ControlRoom1",
            avatar = "https://github.com/twostraws.png",
            description = "A macOS app to control the Xcode Simulator.1",
            language = "Swift1",
            languageColor = "#ffac45",
            stars = "1245",
            forks = "80",
            currentPeriodStars = "441"
        )
    )

    @Spy
    private val trendingRepositoryListLiveData: MutableLiveData<List<GithubRepository>> = MutableLiveData()

    
    @Mock
    private lateinit var repo: TrendingRepository

    @Mock
    private lateinit var api: TrendingRepositoryApiService

    @Mock
    private lateinit var dao: TrendingRepositoryDao


    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {

        `when`(repo.getAllTrendingRepositoriesList()).thenReturn(trendingRepositoryList)

        viewModel = MainActivityViewModel(repo)

        _loadingState = viewModel.loadingState
        //isErrorLiveData = viewModel.shouldShowError()
    }


    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun getAllTrendingRepositoriesShouldShowAndHideLoading() = runBlocking {

        var isLoading = _loadingState.value
        assertNotNull(isLoading)
        isLoading?.let { assertEquals("LOADING", it.status) }
        viewModel.getAllTrendingRepositories()
        verify(repo).getAllTrendingRepositoriesList()
        isLoading = _loadingState.value
        assertNotNull(isLoading)
        isLoading?.let { assertEquals("FINISHED", it.status) }
        return@runBlocking
    }
}
