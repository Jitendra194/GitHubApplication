package com.example.githubapplication.features

import com.example.githubapplication.base.BaseApplication
import retrofit2.Retrofit

enum class FeatureName(val moduleName: String) {
    GitHubRepoSearchFeature("feature_search")
}

interface Feature<T> {
    fun inject(dependencies: T)
}

interface GitHubRepoSearchFeature : Feature<GitHubRepoSearchFeature.Dependencies> {
    interface Dependencies {
        val context: BaseApplication
        val retrofit: Retrofit.Builder
    }
}