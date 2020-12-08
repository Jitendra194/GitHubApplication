package com.example.githubapplication.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.githubapplication.R
import com.example.githubapplication.databinding.ActivityMainBinding
import com.example.githubapplication.di.viewmodel_providers.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels { viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.launchSearchFragment()
    }
}