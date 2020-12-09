package com.example.githubapplication.di.modules

import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.features.GitHubRepoSearchFeature
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object FeaturesModule {

    @Provides
    @Singleton
    fun providesGitHubRepoSearchFeatureDependencies(
        application: BaseApplication,
        retrofit: Retrofit.Builder
    ): GitHubRepoSearchFeature.Dependencies {
        return object : GitHubRepoSearchFeature.Dependencies {
            override val context: BaseApplication
                get() = application
            override val retrofit: Retrofit.Builder
                get() = retrofit
        }
    }
}