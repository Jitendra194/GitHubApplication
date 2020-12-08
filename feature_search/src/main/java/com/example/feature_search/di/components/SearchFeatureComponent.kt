package com.example.feature_search.di.components

import com.example.feature_search.di.modules.FeatureModule
import com.example.feature_search.feature_impl.SearchFeatureImpl
import com.example.githubapplication.features.GitHubRepoSearchFeature
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(
    dependencies = [GitHubRepoSearchFeature.Dependencies::class],
    modules = [FeatureModule::class]
)
interface SearchFeatureComponent : AndroidInjector<SearchFeatureImpl> {

    val repoSearchComponent: RepoSearchComponent.Factory
    val commitsComponent: CommitsComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance searchFeatureImpl: SearchFeatureImpl, gitHubRepoSearchFeature: GitHubRepoSearchFeature.Dependencies): SearchFeatureComponent
    }
}