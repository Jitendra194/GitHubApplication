package com.example.feature_search.github_service

import com.example.feature_search.repository.models.GitHubCommitResponse
import com.example.feature_search.repository.models.GitHubRepositoryResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") repositoryName: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") numberOfRepositoriesToFetch: Int
    ): Single<GitHubRepositoryResponse>

    @GET("repos/{user_name}/{repo_name}/commits")
    fun getCommits(
        @Path("user_name") userName: String,
        @Path("repo_name") repo_name: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") numberOfCommitsToFetch: Int
    ): Single<GitHubCommitResponse>
}