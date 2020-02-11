package com.softaai.dkatalisassignment.trending.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.softaai.dkatalisassignment.R
import com.softaai.dkatalisassignment.databinding.ActivityMainBinding
import com.softaai.dkatalisassignment.trending.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val mainActivityViewModel by viewModel<MainActivityViewModel>()
    //private val mainActivityViewModel: MainActivityViewModel by inject()
    val githubRepositoryListAdapter: GithubRepositoryListAdapter = GithubRepositoryListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.trendingRepositoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
        binding.shimmerViewContainer.startShimmerAnimation()
        val mainActivityViewModel:MainActivityViewModel = get()
        //mainActivityViewModel.passBinding(binding)
        mainActivityViewModel.errorVisibility.value = View.GONE
        binding.viewModel = mainActivityViewModel
        //passBinding(binding)

        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            binding.simpleSwipeRefreshLayout.isRefreshing = false
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmerAnimation()
            mainActivityViewModel.getAllTrendingRepositories()
        }

        mainActivityViewModel.data.observe(this, Observer {
            // Populate the UI
            Toast.makeText(this, "success" + it.size, Toast.LENGTH_LONG).show()
            githubRepositoryListAdapter.updatePostList(it)
            binding.trendingRepositoryList.adapter = githubRepositoryListAdapter
            binding.shimmerViewContainer.stopShimmerAnimation()
            binding.shimmerViewContainer.visibility = View.GONE

        })

        mainActivityViewModel.loadingState.observe(this, Observer {
            // Observe the loading state
            Toast.makeText(this, "error" + it.msg, Toast.LENGTH_LONG).show()
            binding.shimmerViewContainer.stopShimmerAnimation()
            //binding.shimmerViewContainer.visibility = View.GONE

        })
    }
}
