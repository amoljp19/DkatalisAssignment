package com.softaai.dkatalisassignment.trending.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.softaai.dkatalisassignment.R
import com.softaai.dkatalisassignment.databinding.ActivityMainBinding
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val githubRepositoryListAdapter: GithubRepositoryListAdapter = GithubRepositoryListAdapter()
    lateinit var sharedPref: SharedPreferences
    val PREFS_NAME = "githubtrend"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.trendingRepositoryList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
        binding.shimmerViewContainer.startShimmerAnimation()
        sharedPref = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val mainActivityViewModel: MainActivityViewModel = get()
        mainActivityViewModel.errorVisibility.value = View.GONE
        binding.viewModel = mainActivityViewModel

        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            binding.simpleSwipeRefreshLayout.isRefreshing = false
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmerAnimation()
            val minutes =
                TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - getValueLong("TIME")!!)
            if(minutes > 120) {
                mainActivityViewModel.getAllTrendingRepositories()
            }
        }

        mainActivityViewModel.data.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                githubRepositoryListAdapter.updatePostList(it)
                binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
                binding.shimmerViewContainer.stopShimmerAnimation()
                binding.shimmerViewContainer.visibility = View.GONE
                save("TIME", System.currentTimeMillis())
            }

        })

        mainActivityViewModel.loadingState.observe(this, Observer {
            if (it != null) {
                binding.shimmerViewContainer.stopShimmerAnimation()
                //binding.shimmerViewContainer.visibility = View.GONE
            }
        })
    }


    fun save(KEY_NAME: String, text: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_NAME, text)
        editor!!.commit()
    }

    fun getValueLong(KEY_NAME: String): Long? {
        return sharedPref.getLong(KEY_NAME, 0)
    }
}
