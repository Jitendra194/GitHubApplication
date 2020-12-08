package com.example.githubapplication.di.modules

import com.example.githubapplication.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainViewModelProviderModule::class])
    abstract fun contributesMainActivity(): MainActivity
}