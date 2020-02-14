package com.softaai.dkatalisassignment

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
abstract class BaseTest: KoinTest{

    protected val database: TrendingRepositoryDatabase by inject()

    @Before
    open fun setUp() {
        stopKoin()
        this.configureDi()
    }

    @After
    open fun tearDown() {
     stopKoin()
    }


    private fun configureDi() {
        startKoin{
            listOf(configureLocalModuleTest(ApplicationProvider.getApplicationContext<Context>()))
        }
    }

    private fun configureLocalModuleTest(context: Context) = module {
        single {
            Room.inMemoryDatabaseBuilder(context, TrendingRepositoryDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        factory { (get() as TrendingRepositoryDatabase).trendingRepositoryDao() }
    }
}