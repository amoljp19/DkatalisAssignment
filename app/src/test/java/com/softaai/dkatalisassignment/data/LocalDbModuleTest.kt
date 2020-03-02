package com.softaai.dkatalisassignment.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.room.Room
import androidx.room.RoomDatabase
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDao
import com.softaai.dkatalisassignment.data.local.TrendingRepositoryDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Category(CheckModuleTest::class)
class LocalDbModuleTest : KoinTest {
    val PREFS_NAME = "githubtrend"
    val DB_NAME = "trending_repository_db"

    class DiModuleService(private val context: Context) {
        fun getSharedPreference(prefsName: String): SharedPreferences =
            context.getSharedPreferences(prefsName, 0x0000)

        fun getRoomDataBase(dbName: String): RoomDatabase =
            Room.databaseBuilder(
                context, TrendingRepositoryDatabase::class.java,
                dbName
            ).allowMainThreadQueries().build()

        fun getTrendingRepositoryDao(dbName: String): TrendingRepositoryDao = Room.databaseBuilder(
            context, TrendingRepositoryDatabase::class.java,
            dbName
        ).build().trendingRepositoryDao()

    }

    val diServiceModule = module(override = true) {
        factory {
            DiModuleService(
                get()
            )
        }
    }

    val diModuleService: DiModuleService by inject()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(RuntimeEnvironment.application)
            modules(diServiceModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun `testing if we have access to sharedPrefrences using dependency module`() {
        diModuleService.getSharedPreference(PREFS_NAME).edit().putString("TEST", "test").commit()
        Assert.assertEquals(
            "test",
            diModuleService.getSharedPreference(PREFS_NAME).getString("TEST", "")
        )
    }

    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun `testing if we have access to roomDatabase using dependency module`() { //diModuleService.getSharedPreference(PREFS_NAME).edit().putString("TEST", "test").commit()
        Assert.assertEquals(false, diModuleService.getRoomDataBase(DB_NAME).isOpen())
    }

    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @Test
    fun `testing if we have access to trendingRepositoryDao class using dependency module`() {
        Runnable {
            koinApplication {
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

                runBlocking {
                    diModuleService.getTrendingRepositoryDao(DB_NAME)
                        .insertAllTrendingRepositories(githubRepository)
                    Assert.assertEquals(
                        "twostraws",
                        diModuleService.getTrendingRepositoryDao(DB_NAME).allTrendingRepositoryList[0].author
                    )
                }
            }
        }
    }
}