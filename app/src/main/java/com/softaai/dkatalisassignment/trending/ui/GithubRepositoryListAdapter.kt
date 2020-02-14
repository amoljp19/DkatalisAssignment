package com.softaai.dkatalisassignment.trending.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softaai.dkatalisassignment.R
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.databinding.ItemRepositoryBinding
import com.softaai.dkatalisassignment.trending.viewmodel.GithubRepositoryViewModel

class GithubRepositoryListAdapter : RecyclerView.Adapter<GithubRepositoryListAdapter.ViewHolder>() {

    private lateinit var trendingRepositoryList: List<GithubRepository>
    private var clickedNode: Int = -1
    private var previousClickedNode: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repository,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::trendingRepositoryList.isInitialized) trendingRepositoryList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (clickedNode != -1 && clickedNode == position) {
            holder.bind(trendingRepositoryList[position], true)
        } else {
            holder.bind(trendingRepositoryList[position], false)
        }
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (position == clickedNode) {
                    clickedNode = -1;
                } else {
                    if (clickedNode != -1) {
                        previousClickedNode = clickedNode
                        notifyItemChanged(previousClickedNode)
                    }
                    clickedNode = position
                }
                notifyItemChanged(holder.adapterPosition)
            }
        })
    }


    fun updatePostList(trendingRepositoryList: List<GithubRepository>) {
        this.trendingRepositoryList = trendingRepositoryList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = GithubRepositoryViewModel()
        fun bind(repository: GithubRepository, isExpended: Boolean) {
            viewModel.bind(repository, binding, isExpended)
            binding.viewModel = viewModel
        }

    }
}