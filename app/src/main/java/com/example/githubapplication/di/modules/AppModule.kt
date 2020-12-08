package com.example.githubapplication.di.modules

import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.features.FeatureManager
import com.example.githubapplication.features.FeatureManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun providesFeatureManager(application: BaseApplication): FeatureManager = FeatureManagerImpl(application)
}