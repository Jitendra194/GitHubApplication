package com.example.githubapplication.di.modules

import androidx.lifecycle.ViewModel
import com.example.githubapplication.views.MainViewModel
import com.example.githubapplication.di.viewmodel_providers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelProviderModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(viewModel: MainViewModel): ViewModel
}