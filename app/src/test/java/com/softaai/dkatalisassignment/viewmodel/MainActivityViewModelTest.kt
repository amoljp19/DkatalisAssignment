package com.softaai.dkatalisassignment.viewmodel

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softaai.dkatalisassignment.di.githubRepositoryViewModelTestModule
import com.softaai.dkatalisassignment.di.mainActivityViewModelTestModule
import com.softaai.dkatalisassignment.di.trendingRepositoryTestModule
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class MainActivityViewModelTest: KoinTest{

    private val mainActivityViewModel:MainActivityViewModel by inject()

    private val trendingRepository: TrendingRepository by inject()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this@MainActivityViewModelTest)
        startKoin{
            modules(mainActivityViewModelTestModule, trendingRepositoryTestModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }


//    @Test
//    fun testLiveDataNotNull() {
//        MatcherAssert.assertThat(mainActivityViewModel.data, CoreMatchers.notNullValue())
//        MatcherAssert.assertThat(mainActivityViewModel.loadingState, CoreMatchers.notNullValue())
//
//        runBlocking {
//            Mockito.verify(trendingRepository, Mockito.never()).getAllTrendingRepositories()
//        }
//    }
//
//    @Test
//    fun testGetApiResponseCalled() {
//        runBlocking {
//            Mockito.verify(trendingRepository, Mockito.never()).getAllTrendingRepositories()
//        }
//    }

}