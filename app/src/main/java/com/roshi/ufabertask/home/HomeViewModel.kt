package com.roshi.ufabertask.home

import androidx.lifecycle.MutableLiveData
import com.roshi.ufabertask.base.BaseViewModel
import com.roshi.ufabertask.network.ApiInterface
import com.roshi.ufabertask.network.Response
import com.roshi.ufabertask.network.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class HomeViewModel : BaseViewModel() {

    var apiInterface: ApiInterface? = null
        @Inject set
    private var homeRepository: HomeRepository
    private val response: MutableLiveData<Response> = MutableLiveData()
    private val networkStatus: MutableLiveData<Status> = MutableLiveData()


    private val disposables = CompositeDisposable()


    init {
        homeRepository = HomeRepository.getInstance(apiInterface)
        getPublicRepository()
    }


    private fun getPublicRepository() {
        networkStatus.value=Status.LOADING
        disposables.add(
            homeRepository.getPublicRepository()?.subscribeOn(Schedulers.io())?.observeOn(
                AndroidSchedulers.mainThread()
            )?.doOnSubscribe { _ -> response.setValue(Response.loading()) }?.subscribe({
                response.setValue(Response.success(it))
                networkStatus.value=Status.SUCCESS
            }, {
                response.setValue(Response.error(it))
                networkStatus.value=Status.ERROR
            })!!
        )
    }

    fun getResponse(): MutableLiveData<Response> {
        return response
    }

    fun getNetworkStatus(): MutableLiveData<Status> {
        return networkStatus
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}
