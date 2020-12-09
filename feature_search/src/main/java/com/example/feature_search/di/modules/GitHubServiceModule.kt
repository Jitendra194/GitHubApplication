package com.example.feature_search.di.modules

import com.example.feature_search.di.scopes.FeatureScope
import com.example.feature_search.github_service.GitHubBaseConstants
import com.example.feature_search.github_service.GithubService
import com.example.feature_search.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object GitHubServiceModule {
    @Provides
    @FeatureScope
    fun providesGitHubService(retrofit: Retrofit.Builder): GithubService {
        return retrofit.baseUrl(GitHubBaseConstants.GITHUB_BASE_URL)
            .build()
            .create(GithubService::class.java)
    }

    @Provides
    @FeatureScope
    fun providesGitHubRepository(githubService: GithubService): GitHubRepository = GitHubRepository(githubService)
}