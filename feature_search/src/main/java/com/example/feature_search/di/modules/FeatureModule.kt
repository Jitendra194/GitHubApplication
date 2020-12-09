package com.example.feature_search.di.modules

import com.example.feature_search.di.components.CommitsComponent
import com.example.feature_search.di.components.RepoSearchComponent
import com.example.feature_search.di.scopes.FeatureScope
import com.example.feature_search.github_service.GitHubBaseConstants.GITHUB_BASE_URL
import com.example.feature_search.github_service.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(subcomponents = [RepoSearchComponent::class, CommitsComponent::class])
object FeatureModule {
    @Provides
    @FeatureScope
    fun providesGitHubService(retrofit: Retrofit.Builder): GithubService {
        return retrofit.baseUrl(GITHUB_BASE_URL)
            .build()
            .create(GithubService::class.java)
    }
}