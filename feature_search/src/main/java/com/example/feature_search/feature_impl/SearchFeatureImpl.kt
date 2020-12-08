package com.example.feature_search.feature_impl

import com.example.feature_search.di.components.DaggerSearchFeatureComponent
import com.example.feature_search.di.components.SearchFeatureComponent
import com.example.githubapplication.features.GitHubRepoSearchFeature
import com.google.auto.service.AutoService

internal lateinit var searchFeatureComponent: SearchFeatureComponent
    private set

@AutoService(GitHubRepoSearchFeature::class)
class SearchFeatureImpl : GitHubRepoSearchFeature {
    override fun inject(dependencies: GitHubRepoSearchFeature.Dependencies) {
        if (::searchFeatureComponent.isInitialized) {
            return
        } else {
            searchFeatureComponent =
                DaggerSearchFeatureComponent.factory().create(this, dependencies)
        }
    }
}