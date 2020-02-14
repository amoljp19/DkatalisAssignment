package com.softaai.dkatalisassignment.trending.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.softaai.dkatalisassignment.R
import com.softaai.dkatalisassignment.databinding.ActivityMainBinding
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val githubRepositoryListAdapter: GithubRepositoryListAdapter = GithubRepositoryListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.trendingRepositoryList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
        binding.shimmerViewContainer.startShimmerAnimation()
        val mainActivityViewModel: MainActivityViewModel = get()
        mainActivityViewModel.errorVisibility.value = View.GONE
        binding.viewModel = mainActivityViewModel

        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            binding.simpleSwipeRefreshLayout.isRefreshing = false
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmerAnimation()
            mainActivityViewModel.getAllTrendingRepositories()
        }

        mainActivityViewModel.data.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                githubRepositoryListAdapter.updatePostList(it)
                binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
                binding.shimmerViewContainer.stopShimmerAnimation()
                binding.shimmerViewContainer.visibility = View.GONE
            }

        })

        mainActivityViewModel.loadingState.observe(this, Observer {
            if (it != null) {
                binding.shimmerViewContainer.stopShimmerAnimation()
                //binding.shimmerViewContainer.visibility = View.GONE
            }
        })
    }
}
