package com.softaai.dkatalisassignment.viewmodel

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MainActivityViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val trendingRepository = Mockito.mock(TrendingRepository::class.java)
    private val sharedPref = Mockito.mock(SharedPreferences::class.java)
    private val mainActivityViewModel = MainActivityViewModel(trendingRepository)

    @Test
    fun testLiveDataNotNull() {
        MatcherAssert.assertThat(mainActivityViewModel.data, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(mainActivityViewModel.loadingState, CoreMatchers.notNullValue())

        runBlocking {
            Mockito.verify(trendingRepository, Mockito.never()).getAllTrendingRepositories()
        }
    }

    @Test
    fun testGetApiResponseCalled() {
        runBlocking {
            Mockito.verify(trendingRepository, Mockito.never()).getAllTrendingRepositories()
        }
    }
}