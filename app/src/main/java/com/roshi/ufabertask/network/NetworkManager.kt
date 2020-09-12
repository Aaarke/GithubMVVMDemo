package com.roshi.ufabertask.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class NetworkManager(context: Context) {
    private val factory: Factory = Factory()
    private val callBack: CallBack = CallBack()
    private val connectivityManager: ConnectivityManager
    var result: LiveData<NetworkResult> = callBack.result
    fun registerCallBack() {
        val networkRequest: NetworkRequest = factory.wifiRequest
        connectivityManager.registerNetworkCallback(networkRequest, callBack)
    }

    fun unRegisterCallBack() {
        connectivityManager.unregisterNetworkCallback(callBack)
    }

    init {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}