package com.example.githubapplication.views

import androidx.lifecycle.ViewModel
import com.example.githubapplication.features.FeatureManager
import com.example.githubapplication.features.FeatureName.*
import com.example.githubapplication.features.getFeature
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val featureManager: FeatureManager
) : ViewModel() {
    fun launchSearchFragment() = featureManager getFeature GitHubRepoSearchFeature
}