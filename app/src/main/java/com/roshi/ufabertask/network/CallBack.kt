package com.roshi.ufabertask.network

import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
internal class CallBack : NetworkCallback() {
    val result = MutableLiveData<NetworkResult>()
    override fun onLosing(network: Network, maxMsToLive: Int) {
        result.postValue(NetworkResult.DISCONNECTING)
    }

    override fun onAvailable(network: Network) {
        result.postValue(NetworkResult.CONNECTED)
    }

    override fun onLost(network: Network) {
        result.postValue(NetworkResult.DISCONNECTED)
    }
}