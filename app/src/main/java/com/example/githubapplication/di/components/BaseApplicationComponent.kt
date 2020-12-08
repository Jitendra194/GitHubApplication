package com.example.githubapplication.di.components

import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.di.modules.ActivityBuilderModule
import com.example.githubapplication.di.modules.AppModule
import com.example.githubapplication.di.modules.FeaturesModule
import com.example.githubapplication.di.viewmodel_providers.ViewModelFactoryModule
import com.example.githubapplication.features.GitHubRepoSearchFeature
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        FeaturesModule::class
    ]
)
interface BaseApplicationComponent : AndroidInjector<BaseApplication> {

    val gitHubRepoSearchFeature: GitHubRepoSearchFeature.Dependencies

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApplication): BaseApplicationComponent
    }
}