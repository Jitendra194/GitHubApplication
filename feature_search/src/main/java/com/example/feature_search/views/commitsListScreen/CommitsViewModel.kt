package com.example.feature_search.views.commitsListScreen

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_search.repository.GitHubRepository
import com.example.feature_search.repository.models.GitHubCommitResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommitsViewModel @Inject constructor(private val gitHubRepository: GitHubRepository) : ViewModel() {

    val progressVisibility = ObservableBoolean(false)

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _commitResponse = MutableLiveData<GitHubCommitResponse>()
    val commitResponse: LiveData<GitHubCommitResponse>
        get() = _commitResponse

    fun getCommitsForRepo(repoAndUserName: String) {
        val userName = repoAndUserName.substringBefore("/")
        val repoName = repoAndUserName.substringAfter("/")
        progressVisibility.set(true)
        compositeDisposable.add(gitHubRepository.getCommits(userName, repoName)
            .doFinally { progressVisibility.set(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = this::onSuccess, onError = this::onError))
    }

    private fun onSuccess(response: GitHubCommitResponse) {
        _commitResponse.value = response
    }

    private fun onError(throwable: Throwable) {
        println(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}