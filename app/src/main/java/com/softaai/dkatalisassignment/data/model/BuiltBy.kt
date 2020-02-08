package com.softaai.dkatalisassignment.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuiltBy(
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "href")
    val href: String,
    @Json(name = "username")
    val username: String
)