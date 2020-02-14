package com.softaai.weatherforecast.data

import com.softaai.dkatalisassignment.data.local.GithubRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ApiResponseTest {
    @Test
    fun apiResponseSuccess() {
        val apiResponseSuccess: Response<List<GithubRepository>> =
            Response.success("abc") as Response<List<GithubRepository>>

        assertThat(apiResponseSuccess.body().toString(), `is`("abc"))

    }

    @Test
    fun apiResponseError() {
        val apiResponseError: Response<List<GithubRepository>> = Response.error<String>(
            400,
            ResponseBody.create("application/json".toMediaTypeOrNull(), "Response.error()")
        ) as Response<List<GithubRepository>>

        assertThat(apiResponseError.message(), `is`("Response.error()"))
    }
}