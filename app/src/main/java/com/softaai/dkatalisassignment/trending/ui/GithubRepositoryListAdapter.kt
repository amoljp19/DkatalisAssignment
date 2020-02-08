package com.softaai.dkatalisassignment.trending.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel

class GithubRepositoryListAdapter: RecyclerView.Adapter<GithubRepositoryListAdapter.ViewHolder>(){


    class ViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = GithubRepositoryViewModel()
        fun bind(repository: GithubRepository, isExpended: Boolean) {
            viewModel.bind(repository, binding, isExpended)
            binding.viewModel = viewModel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}