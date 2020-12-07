package com.example.githubapplication.features

enum class FeatureName(val moduleName: String) {
    GitHubRepoSearchFeature("feature-search"),
    GitHubRepoDetailsFeature("feature-repo-details")
}

interface Feature<T> {
    fun inject(dependencies: T)
}

interface GitHubRepoSearchFeature : Feature<GitHubRepoSearchFeature.Dependencies> {
    interface Dependencies {
    }
}

interface GitHubRepoDetailsFeature : Feature<GitHubRepoDetailsFeature.Dependencies> {
    interface Dependencies {
    }
}