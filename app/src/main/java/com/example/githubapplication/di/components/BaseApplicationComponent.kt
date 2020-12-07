package com.example.githubapplication.di.components

import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.di.modules.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class
    ]
)
interface BaseApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApplication): BaseApplicationComponent
    }
}