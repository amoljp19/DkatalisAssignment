package com.softaai.dkatalisassignment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrendingRepository(@field:PrimaryKey(autoGenerate = true)
                      val id: Int,
                      val author:String,
                      val name:String,
                      val avatar:String,
                      val description:String,
                      val language:String? = "language",
                      val languageColor:String? ="#000000",
                      val stars:String,
                      val forks:String,
                      val currentPeriodStars: String)