package com.example.feature_search.repository

import com.example.feature_search.github_service.GithubService
import com.example.feature_search.repository.models.GitHubCommitResponse
import com.example.feature_search.repository.models.GitHubRepositoryResponse
import io.reactivex.Observable
import io.reactivex.Single

class GitHubRepository(private val githubService: GithubService) {

    private companion object {
        private const val PAGE_NUMBER = 1
        private const val PAGE_ITEM_SIZE = 25
    }

    fun getRepositories(searchString: String): Single<GitHubRepositoryResponse> {
        return githubService.getRepositories(searchString, PAGE_NUMBER, PAGE_ITEM_SIZE)
    }

    fun getCommits(userName: String, repoName: String): Single<GitHubCommitResponse> {
        return githubService.getCommits(userName, repoName, PAGE_NUMBER, PAGE_ITEM_SIZE)
    }

}