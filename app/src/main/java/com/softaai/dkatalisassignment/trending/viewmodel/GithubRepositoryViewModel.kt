package com.softaai.dkatalisassignment.trending.viewmodel

import androidx.lifecycle.ViewModel
import com.softaai.dkatalisassignment.data.local.GithubRepository
import com.softaai.dkatalisassignment.databinding.ItemRepositoryBinding

class GithubRepositoryViewModel : ViewModel() {

    var repository: GithubRepository? = null
    var isExpanded: Boolean = false

    fun bind(
        repository: GithubRepository,
        binding: ItemRepositoryBinding,
        expended: Boolean
    ) {
        repository.languageColor?.let { binding.circularView.setSolidColor(it) }
        this.repository = repository;
        this.isExpanded = expended
    }

}