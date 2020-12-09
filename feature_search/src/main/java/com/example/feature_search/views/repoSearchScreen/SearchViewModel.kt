package com.example.feature_search.views.repoSearchScreen

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.feature_search.repository.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    val repositoryName = ObservableField<String>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getRepositoryData() {
        compositeDisposable.add(gitHubRepository.getRepositories(repositoryName.get() ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = this::onSuccess, onError = this::onError)
        )
    }

    private fun onError(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun onSuccess(response: String) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}