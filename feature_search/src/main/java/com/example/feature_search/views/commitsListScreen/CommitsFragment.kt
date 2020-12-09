package com.example.feature_search.views.commitsListScreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.feature_search.R
import com.example.feature_search.databinding.CommitsFragmentBinding
import com.example.feature_search.feature_impl.searchFeatureComponent
import com.example.feature_search.repository.models.Item
import com.example.githubapplication.di.viewmodel_providers.ViewModelProviderFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.commits_fragment.*
import javax.inject.Inject

class CommitsFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val commitsViewModel: CommitsViewModel by viewModels { viewModelProviderFactory }
    private val args: CommitsFragmentArgs by navArgs()

    private lateinit var binding: CommitsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchFeatureComponent.commitsComponent
            .create(this)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<CommitsFragmentBinding>(inflater, R.layout.commits_fragment, container, false).run {
        binding = this
        viewModel = commitsViewModel
        lifecycleOwner = viewLifecycleOwner
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        commitsViewModel.getCommitsForRepo(args.reponeNameAndUser)
    }

    private fun observeLiveData() = commitsViewModel.apply {
        commitResponse.observe(viewLifecycleOwner) {
            commits_list.adapter = GitHubCommitsAdapter(it)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}