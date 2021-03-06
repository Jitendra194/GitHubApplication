package com.example.feature_search.di.modules.viewmodel_providers

import androidx.lifecycle.ViewModel
import com.example.feature_search.views.repoSearchScreen.SearchViewModel
import com.example.githubapplication.di.viewmodel_providers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class SearchViewModelProviderModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}