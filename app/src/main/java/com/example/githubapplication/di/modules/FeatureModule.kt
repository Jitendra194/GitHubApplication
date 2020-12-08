package com.example.githubapplication.di.modules

import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.features.GitHubRepoSearchFeature
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FeatureModule {

    @Provides
    @Singleton
    fun providesGitHubRepoSearchFeatureDependencies(application: BaseApplication): GitHubRepoSearchFeature.Dependencies {
        return object : GitHubRepoSearchFeature.Dependencies {
            override val context: BaseApplication
                get() = application
        }
    }
}