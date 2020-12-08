package com.example.feature_search.di.components

import com.example.feature_search.di.modules.CommitsModule
import com.example.feature_search.views.RepoSearchFragment
import com.example.feature_search.di.modules.RepoSearchModule
import com.example.feature_search.di.modules.viewmodel_providers.CommitsViewModelProviderModule
import com.example.feature_search.di.modules.viewmodel_providers.SearchViewModelProviderModule
import com.example.feature_search.di.scopes.FeatureScope
import com.example.feature_search.views.CommitsFragment
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@FeatureScope
@Subcomponent(modules = [AndroidInjectionModule::class, SearchViewModelProviderModule::class, RepoSearchModule::class])
interface RepoSearchComponent : AndroidInjector<RepoSearchFragment> {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance repoSearchFragment: RepoSearchFragment): RepoSearchComponent
    }
}

@FeatureScope
@Subcomponent(modules = [AndroidInjectionModule::class, CommitsViewModelProviderModule::class, CommitsModule::class])
interface CommitsComponent : AndroidInjector<CommitsFragment> {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance commitsFragment: CommitsFragment): CommitsComponent
    }
}