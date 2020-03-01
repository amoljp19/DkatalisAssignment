package com.softaai.dkatalisassignment.di

import android.content.Context
import android.os.Build
import com.softaai.dkatalisassignment.R
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class ContextTest : KoinTest {

    class ContextService(private val context: Context) {
        fun getString(stringID: Int): String = context.getString(stringID)
    }

    val contextService: ContextService by inject()

    @Before
    fun setUp() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(RuntimeEnvironment.application)
            modules(diModule)
//            modules(listOf(mainActivityViewModelModule, githubRepositoryViewModel,
//                trendingRepositoryModule, trendingRepositoryDbModule,
//                trendingRepositoryDaoModule, spUtilsModule, apiModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun `testing if we have access to context`() {
        Assert.assertEquals("DkatalisAssignment", contextService.getString(R.string.app_name))
    }
}