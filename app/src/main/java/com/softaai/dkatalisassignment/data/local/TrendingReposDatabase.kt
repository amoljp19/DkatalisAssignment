package com.softaai.dkatalisassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TrendingRepository::class), version = 1)
abstract class TrendingReposDatabase : RoomDatabase()  {
    abstract fun repositoryDao(): TrendingRepositoryDao
}