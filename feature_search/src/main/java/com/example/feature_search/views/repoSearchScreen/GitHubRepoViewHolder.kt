package com.example.feature_search.views.repoSearchScreen

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search.databinding.RepoItemBinding
import com.example.feature_search.repository.models.Item

class GitHubRepoViewHolder(
    private val binding: RepoItemBinding,
    private val repoListener: GitHubRepoAdapter.ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(repoItem: Item) = binding.run {
        item = repoItem
        listener = repoListener
        executePendingBindings()
    }
}