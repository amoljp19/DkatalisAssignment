package com.softaai.dkatalisassignment.trending.viewmodel

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.data.remote.LoadingState
import com.softaai.dkatalisassignment.databinding.ActivityMainBinding
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.ui.GithubRepositoryListAdapter
import com.softaai.dkatalisassignment.utils.SPUtils
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repo: TrendingRepository): ViewModel(), Callback<List<GithubRepository>> {

    private val parentJob = Job()

    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    //val spUtils: SPUtils = SPUtils(binding.root.context)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val errorClickListener = View.OnClickListener { getAllTrendingRepositories() }

    val githubRepositoryListAdapter: GithubRepositoryListAdapter = GithubRepositoryListAdapter()


    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<List<GithubRepository>>()
    val data: LiveData<List<GithubRepository>>
        get() = _data

    init {
        getAllTrendingRepositories()
//        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
//            binding.simpleSwipeRefreshLayout.isRefreshing = false
//            getAllTrendingRepositories()
//        }
    }

    private fun getAllTrendingRepositories(){
        scope.launch {
            fetchData()
        }
    }


    private suspend fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
       // binding.shimmerViewContainer.startShimmerAnimation()
        repo.getAllTrendingRepositories().enqueue(this)
        //binding.shimmerViewContainer.stopShimmerAnimation()
    }

    override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
        //binding.shimmerViewContainer.stopShimmerAnimation()
    }

    override fun onResponse(call: Call<List<GithubRepository>>, response: Response<List<GithubRepository>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            response.body()?.let { githubRepositoryListAdapter.updatePostList(it) }
            _loadingState.postValue(LoadingState.LOADED)
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
        }
        //binding.shimmerViewContainer.stopShimmerAnimation()
    }

    fun cancelRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        cancelRequests()
    }


    fun onClickToolBarMenu(v: View) {
        showFilterPopup(v)
    }

    private fun showFilterPopup(v: View) {
        val popup =
            PopupMenu(v.context, v, Gravity.END, 0, com.softaai.dkatalisassignment.R.style.OverflowMenu)
        popup.inflate(com.softaai.dkatalisassignment.R.menu.menu_main)

        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    else -> return false
                }
            }
        })
        popup.show()
    }

}