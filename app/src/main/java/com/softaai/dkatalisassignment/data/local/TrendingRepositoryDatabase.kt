package com.softaai.dkatalisassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(GithubRepository::class), version = 1)
abstract class TrendingRepositoryDatabase : RoomDatabase()  {
    abstract fun trendingRepositoryDao(): TrendingRepositoryDao
}