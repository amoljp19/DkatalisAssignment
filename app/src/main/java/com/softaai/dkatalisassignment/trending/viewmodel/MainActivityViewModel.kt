package com.softaai.dkatalisassignment.trending.viewmodel

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.trendrepo.utils.SPUtils
import com.softaai.dkatalisassignment.data.model.TrendingRepositoryResponse
import com.softaai.dkatalisassignment.data.remote.LoadingState
import com.softaai.dkatalisassignment.databinding.ActivityMainBinding
import com.softaai.dkatalisassignment.repository.TrendingRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repo: TrendingRepository, private val binding: ActivityMainBinding): ViewModel(), Callback<List<TrendingRepositoryResponse>> {

    private val parentJob = Job()

    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<List<TrendingRepositoryResponse>>()
    val data: LiveData<List<TrendingRepositoryResponse>>
        get() = _data

    init {
        getAllTrendingRepositories()
        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            binding.simpleSwipeRefreshLayout.isRefreshing = false
            getAllTrendingRepositories()
        }
    }

    private fun getAllTrendingRepositories(){
        scope.launch {
            fetchData()
        }
    }


    private suspend fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        repo.getAllTrendingRepositories().enqueue(this)
    }

    override fun onFailure(call: Call<List<TrendingRepositoryResponse>>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    override fun onResponse(call: Call<List<TrendingRepositoryResponse>>, response: Response<List<TrendingRepositoryResponse>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
        }
    }

    fun cancelRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        cancelRequests()
    }
}