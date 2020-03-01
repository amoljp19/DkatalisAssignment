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
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class ContextTest : AutoCloseKoinTest() {

    class ContextService(private val context: Context) {
        fun getString(stringID: Int): String = context.getString(stringID)
    }

    val myModule = module(override=true){
        factory { ContextService(get()) }
    }


    val contextService: ContextService by inject()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(RuntimeEnvironment.application)
            modules(myModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    //ToDo to avoid below warning
    // [Robolectric] WARN: Android SDK 29 requires Java 9 (have Java 8). Tests won't be run on SDK 29 unless explicitly requested,
    // also test failed added below @Config annotation, as
    // latest version of Robolectric, requires java 9 or greater
    // when it run on android Q build version, my java version is 8
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun `testing if we have access to context`() {
        Assert.assertEquals("DkatalisAssignment", contextService.getString(R.string.app_name))
    }
}