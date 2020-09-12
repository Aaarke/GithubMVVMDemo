package com.roshi.ufabertask.home

import androidx.lifecycle.MutableLiveData
import com.roshi.ufabertask.base.BaseViewModel
import com.roshi.ufabertask.network.ApiInterface
import com.roshi.ufabertask.network.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class HomeViewModel : BaseViewModel() {

    var apiInterface: ApiInterface? = null
        @Inject set
    private var homeRepository: HomeRepository
    private val response: MutableLiveData<Response> = MutableLiveData()

    private val disposables = CompositeDisposable()


    init {
        homeRepository = HomeRepository.getInstance(apiInterface)
        getPublicRepository()
    }


    private fun getPublicRepository() {
        disposables.add(
            homeRepository.getPublicRepository()?.subscribeOn(Schedulers.io())?.observeOn(
                AndroidSchedulers.mainThread()
            )?.doOnSubscribe { _ -> response.setValue(Response.loading()) }?.subscribe({
                response.setValue(Response.success(it))
            }, {
                response.setValue(Response.error(it))
            })!!
        )
    }

    fun getResponse(): MutableLiveData<Response> {
        return response
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}
