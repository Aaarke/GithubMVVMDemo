package com.roshi.ufabertask.repowner

import android.view.View
import androidx.lifecycle.MutableLiveData

import com.roshi.ufabertask.R
import com.roshi.ufabertask.base.BaseViewModel
import com.roshi.ufabertask.model.Item
import com.roshi.ufabertask.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoOwnerViewModel : BaseViewModel() {
    var apiInterface: ApiInterface? = null
        @Inject set
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private val subscription = CompositeDisposable()
    var listOfRepo = MutableLiveData<ArrayList<Item>>()


    fun getListOfRepos(url: String) {
        subscription.add(
            apiInterface?.getAllRepoOfUser(url)!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveContributerRepoListStart() }
                .doOnTerminate { onRetrieveContributerRepoListFinish() }
                .subscribe(
                    { result -> onRetrieveContributerRepoListSuccess(result) },
                    { onRetrieveContributerRepoListError() }
                )
        )
    }

    private fun onRetrieveContributerRepoListError() {
        errorMessage.value = R.string.post_error

    }

    private fun onRetrieveContributerRepoListSuccess(result: ArrayList<Item>) {
        listOfRepo.value = result
    }

    private fun onRetrieveContributerRepoListFinish() {
        loadingVisibility.value = View.GONE

    }

    private fun onRetrieveContributerRepoListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}