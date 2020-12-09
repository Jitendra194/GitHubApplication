package com.example.feature_search.di.modules.viewmodel_providers

import androidx.lifecycle.ViewModel
import com.example.feature_search.views.commitsListScreen.CommitsViewModel
import com.example.githubapplication.di.viewmodel_providers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class CommitsViewModelProviderModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommitsViewModel::class)
    abstract fun bindCommitsViewModel(viewModel: CommitsViewModel): ViewModel
}