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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val mainActivityViewModel:MainActivityViewModel = get()
        //mainActivityViewModel.passBinding(binding)
        binding.viewModel = mainActivityViewModel
        //passBinding(binding)

//        mainActivityViewModel.data.observe(this, Observer {
//            // Populate the UI
//            Toast.makeText(this, "" + it.size, Toast.LENGTH_LONG)
//        })
//
//        mainActivityViewModel.loadingState.observe(this, Observer {
//            // Observe the loading state
//            Toast.makeText(this, "" + it.msg, Toast.LENGTH_LONG)
//        })
    }
}
