package com.roshi.ufabertask.network

import com.roshi.ufabertask.R


enum class NetworkResult(colorConnected: Int, messageConnected: Int) {
    CONNECTED(
        R.color.colorConnected,
        R.string.messageConnected
    ),
    DISCONNECTED(
        R.color.colorDisconnected,
        R.string.messageDisconnected
    ),
    DISCONNECTING(R.color.colorDisconnecting, R.string.messageDisconnecting)
}