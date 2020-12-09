package com.example.feature_search.views.commitsListScreen

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search.databinding.CommitItemBinding
import com.example.feature_search.databinding.RepoItemBinding
import com.example.feature_search.repository.models.CommitItem
import com.example.feature_search.repository.models.GitHubCommitResponse
import com.example.feature_search.repository.models.Item

class GitHubCommitsViewHolder(private val binding: CommitItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(commitItem: CommitItem) = binding.run {
        item = commitItem
        executePendingBindings()
    }
}