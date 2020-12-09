package com.example.feature_search.repository

import com.example.feature_search.github_service.GithubService

class GitHubRepository(private val githubService: GithubService) {
    fun getRepositories(searchString: String) = githubService.getRepositories(searchString)
}