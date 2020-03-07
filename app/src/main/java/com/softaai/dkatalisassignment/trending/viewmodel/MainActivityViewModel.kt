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
import com.softaai.dkatalisassignment.repository.TrendingRepository
import com.softaai.dkatalisassignment.trending.ui.GithubRepositoryListAdapter
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(val repo: TrendingRepository) :
    ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorClickListener = View.OnClickListener { getAllTrendingRepositories() }

    val githubRepositoryListAdapter: GithubRepositoryListAdapter = GithubRepositoryListAdapter()


    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<List<GithubRepository>>()
    val data: LiveData<List<GithubRepository>>
        get() = _data

    var response: Response<List<GithubRepository>> = Response.success(listOf(
        GithubRepository(
            id = 0,
            author = "twostraws",
            name = "ControlRoom",
            avatar = "https://github.com/twostraws.png",
            description = "A macOS app to control the Xcode Simulator.",
            language = "Swift",
            languageColor = "#ffac45",
            stars = "1245",
            forks = "70",
            currentPeriodStars = "440"
        )
    ))

    init {
        getAllTrendingRepositories()
    }


    fun getAllTrendingRepositories() {
        scope.launch {
            _loadingState.postValue(LoadingState.LOADING)

            getAllTrendingRepositoriesFromRemote()

        }
    }

    fun postDataFromLocalDb() {
        scope.launch {
            _data.postValue(repo.getAllTrendingRepositoriesList())
        }
    }

    suspend fun getAllTrendingRepositoriesFromRemote() {
        response = repo.getAllTrendingRepositories()

        if (response.isSuccessful) {
            _loadingState.postValue(LoadingState.LOADED)
            repo.insertAllTrendingRepositories(*response.body()!!.toTypedArray())
            postDataFromLocalDb()
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
            errorVisibility.value = View.VISIBLE
        }
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
            PopupMenu(
                v.context,
                v,
                Gravity.END,
                0,
                com.softaai.dkatalisassignment.R.style.OverflowMenu
            )
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