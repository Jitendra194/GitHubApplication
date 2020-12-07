package com.example.githubapplication.base

import com.example.githubapplication.di.components.BaseApplicationComponent
import com.example.githubapplication.di.components.DaggerBaseApplicationComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

internal lateinit var appComponent: BaseApplicationComponent
    private set

class BaseApplication : SplitCompatApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerBaseApplicationComponent.factory().create(this)
        appComponent.inject(this)
    }
}