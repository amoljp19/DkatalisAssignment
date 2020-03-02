package com.softaai.weatherforecast.data

import arrow.core.none
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.remote.TrendingRepositoryApiService
import com.softaai.dkatalisassignment.repository.TrendingRepository
import kotlinx.coroutines.runBlocking
import me.jorgecastillo.hiroaki.*
import me.jorgecastillo.hiroaki.internal.MockServerSuite
import me.jorgecastillo.hiroaki.models.fileBody
import me.jorgecastillo.hiroaki.models.success
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.converter.moshi.MoshiConverterFactory


@RunWith(MockitoJUnitRunner::class)
class TrendingRepositoryTest : KoinTest, MockServerSuite() {

    private val trendingRepository: TrendingRepository by inject()

    @Before
    override fun setup() {
        super.setup()
        server.retrofitService(
            TrendingRepositoryApiService::class.java,
            MoshiConverterFactory.create()
        )
        //runBlocking { trendingRepository.getAllTrendingRepositories() }
        Runnable {
            koinApplication {
                runBlocking{  trendingRepository.getAllTrendingRepositories() }
            }
        }
    }


    @Test
    fun getApiResponse() {

        server.whenever(Method.GET, "/repositories")
            .thenRespond(success(jsonBody = fileBody("api_response.json")))

        //runBlocking { trendingRepository.getAllTrendingRepositories() }

        Runnable {
            koinApplication {
                runBlocking{  trendingRepository.getAllTrendingRepositories() }
            }
        }

        server.verify("/repositories").called(
            times = once(),
            headers = headers(
                "Cache-Control" to "max-age=120"
            ),
            method = Method.GET
        )

    }

    @After
    fun after() {
        stopKoin()
    }

}