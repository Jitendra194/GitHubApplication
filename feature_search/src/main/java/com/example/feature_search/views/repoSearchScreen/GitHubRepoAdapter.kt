package com.example.feature_search.views.repoSearchScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search.R
import com.example.feature_search.databinding.RepoItemBinding
import com.example.feature_search.repository.models.Item

class GitHubRepoAdapter(private val repoItems: List<Item>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<GitHubRepoViewHolder>() {

    interface ItemClickListener {
        fun onClickRepository(view: View, repoItem: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder =
        DataBindingUtil.inflate<RepoItemBinding>(LayoutInflater.from(parent.context), R.layout.repo_item, parent, false).run {
            GitHubRepoViewHolder(this, itemClickListener)
        }

    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        holder.bind(repoItems[position])
    }

    override fun getItemCount(): Int = repoItems.size
}