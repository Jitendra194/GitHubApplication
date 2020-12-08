package com.example.feature_search.di.modules

import com.example.feature_search.di.components.CommitsComponent
import com.example.feature_search.di.components.RepoSearchComponent
import dagger.Module

@Module(subcomponents = [RepoSearchComponent::class, CommitsComponent::class])
object FeatureModule