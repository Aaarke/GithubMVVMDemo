package com.roshi.ufabertask.network

import com.roshi.ufabertask.model.GitData
import com.roshi.ufabertask.model.Item
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    /**
     * Get the list of the pots from the API
     */
    @GET("repositories")
    fun getRepo(): Observable<List<GitData>>
    @GET
    fun getContriButers(@Url contriUrl: String): Observable<ArrayList<Item>>

}
