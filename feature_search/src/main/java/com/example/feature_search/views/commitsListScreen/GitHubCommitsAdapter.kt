package com.example.feature_search.views.commitsListScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search.R
import com.example.feature_search.databinding.CommitItemBinding
import com.example.feature_search.databinding.RepoItemBinding
import com.example.feature_search.repository.models.GitHubCommitResponse
import com.example.feature_search.repository.models.Item

class GitHubCommitsAdapter(private val commits: GitHubCommitResponse) :
    RecyclerView.Adapter<GitHubCommitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubCommitsViewHolder =
        DataBindingUtil.inflate<CommitItemBinding>(LayoutInflater.from(parent.context), R.layout.commit_item, parent, false).run {
            GitHubCommitsViewHolder(this)
        }

    override fun onBindViewHolder(holder: GitHubCommitsViewHolder, position: Int) {
        holder.bind(commits[position])
    }

    override fun getItemCount(): Int = commits.size
}