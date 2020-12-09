package com.example.feature_search.views.repoSearchScreen

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_search.repository.GitHubRepository
import com.example.feature_search.repository.models.GitHubRepositoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    val repositoryName = ObservableField<String>()
    val progressVisibility = ObservableBoolean(false)

    private val _repoResponse = MutableLiveData<GitHubRepositoryResponse>()
    val repoResponse: LiveData<GitHubRepositoryResponse>
        get() = _repoResponse

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getRepositoryData() {
        progressVisibility.set(true)
        compositeDisposable.add(
            gitHubRepository.getRepositories(repositoryName.get() ?: "")
                .doFinally { progressVisibility.set(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = this::onSuccess, onError = this::onError)
        )
    }

    private fun onError(throwable: Throwable) {
        println(throwable)
    }

    private fun onSuccess(response: GitHubRepositoryResponse) {
        _repoResponse.value = response
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}