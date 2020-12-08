package com.example.feature_search.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feature_search.R
import com.example.feature_search.databinding.FragmentRepoSearchBinding
import com.example.feature_search.feature_impl.searchFeatureComponent
import com.example.githubapplication.di.viewmodel_providers.ViewModelProviderFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RepoSearchFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val searchViewModel: SearchViewModel by viewModels { viewModelProviderFactory }

    private lateinit var binding: FragmentRepoSearchBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchFeatureComponent.repoSearchComponent
            .create(this)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentRepoSearchBinding>(inflater,
        R.layout.fragment_repo_search, container, false).run {
        binding = this
        viewModel = searchViewModel
        lifecycleOwner = viewLifecycleOwner
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        findNavController().navigate(RepoSearchFragmentDirections.actionRepoSearchFragmentToCommitsFragment())
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}