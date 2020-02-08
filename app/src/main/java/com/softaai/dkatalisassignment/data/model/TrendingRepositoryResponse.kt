package com.softaai.dkatalisassignment.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingRepositoryResponse(
    @Json(name = "author")
    val author: String,
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "builtBy")
    val builtBy: List<BuiltBy>,
    @Json(name = "currentPeriodStars")
    val currentPeriodStars: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "forks")
    val forks: Int,
    @Json(name = "language")
    val language: String,
    @Json(name = "languageColor")
    val languageColor: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "stars")
    val stars: Int,
    @Json(name = "url")
    val url: String
)