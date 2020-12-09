package com.example.feature_search.views.repoSearchScreen

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feature_search.R
import com.example.feature_search.databinding.FragmentRepoSearchBinding
import com.example.feature_search.feature_impl.searchFeatureComponent
import com.example.feature_search.repository.models.Item
import com.example.githubapplication.di.viewmodel_providers.ViewModelProviderFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_repo_search.*
import javax.inject.Inject

class RepoSearchFragment : Fragment(), HasAndroidInjector, GitHubRepoAdapter.ItemClickListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var binding: FragmentRepoSearchBinding
    private lateinit var gitHubRepoAdapter: GitHubRepoAdapter

    private val searchViewModel: SearchViewModel by viewModels { viewModelProviderFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchFeatureComponent.repoSearchComponent
            .create(this)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentRepoSearchBinding>(inflater, R.layout.fragment_repo_search, container, false).run {
        binding = this
        viewModel = searchViewModel
        lifecycleOwner = viewLifecycleOwner
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        setupKeyboardDoneButton()
    }

    private fun setupKeyboardDoneButton() = search_text_field.setOnEditorActionListener { _, i, keyEvent ->
        if ((keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
            searchViewModel.getRepositoryData()
        }
        false
    }

    private fun observeLiveData() = searchViewModel.apply {
        repoResponse.observe(viewLifecycleOwner) {
            gitHubRepoAdapter = GitHubRepoAdapter(it.items, this@RepoSearchFragment)
            repo_list.adapter = gitHubRepoAdapter
        }
    }

    override fun onClickRepository(view: View, repoItem: Item) {
        findNavController().navigate(RepoSearchFragmentDirections.actionRepoSearchFragmentToCommitsFragment(repoItem.full_name))
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}