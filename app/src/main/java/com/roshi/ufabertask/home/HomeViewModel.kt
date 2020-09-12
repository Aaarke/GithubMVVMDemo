package com.roshi.ufabertask.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.roshi.ufabertask.base.BaseViewModel
import com.roshi.ufabertask.model.GitData
import com.roshi.ufabertask.network.ApiInterface
import java.util.concurrent.Executor
import javax.inject.Inject


class HomeViewModel : BaseViewModel(){

    var apiInterface: ApiInterface? = null
        @Inject set
    var errorMessage: MutableLiveData<String>? = MutableLiveData()
    var executor: Executor? = null
    var pagedListLiveData: LiveData<PagedList<GitData>>? = null
    var filterTextAll = MutableLiveData<String>()
    private lateinit var homeRepository:HomeRepository

    init {
    }




}
