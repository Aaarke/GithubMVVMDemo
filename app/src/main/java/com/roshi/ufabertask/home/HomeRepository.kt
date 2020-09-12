package com.roshi.ufabertask.home

import com.roshi.ufabertask.model.GitData
import com.roshi.ufabertask.network.ApiInterface
import io.reactivex.Observable

class HomeRepository(private val apiInterface: ApiInterface?) {
    companion object {
        private val mInstance: HomeRepository? = null
        fun getInstance(apiInterface: ApiInterface?): HomeRepository {
            return mInstance ?: HomeRepository(apiInterface)
        }
    }


    fun getPublicRepository(): Observable<List<GitData>>? {
        return apiInterface?.getRepo()
    }


}