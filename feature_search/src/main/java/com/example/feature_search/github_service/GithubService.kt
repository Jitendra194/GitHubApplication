package com.example.feature_search.github_service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("repositories")
    fun getRepositories(@Query("q") repositoryName: String): Observable<String>

    @GET("repos/{repo_commits}/commits")
    fun getCommits(@Path("repo_commits") repoAndUserName: String, @Query("per_page") numberOfCommitsToFetch: Int): Observable<String>
}