package com.roshi.ufabertask.base

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.roshi.ufabertask.R
import com.roshi.ufabertask.network.NetworkManager
import com.roshi.ufabertask.network.NetworkResult
import com.roshi.ufabertask.utility.Codes.SnackBarType.Companion.ERROR
import com.roshi.ufabertask.utility.Codes.SnackBarType.Companion.MESSAGE
import com.roshi.ufabertask.utility.Utils

open class BaseActivity : AppCompatActivity() {
    private lateinit var networkManager: NetworkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setActionBarUi()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkManager = NetworkManager(this)
            networkManager.result.observe(this) { networkResult ->
                when (networkResult) {
                    NetworkResult.CONNECTED -> {
                    }
                    NetworkResult.DISCONNECTED -> Utils.snackBar(
                        this@BaseActivity,
                        "No Internet Connection Available",
                        ERROR
                    )
                    NetworkResult.DISCONNECTING -> Utils.snackBar(
                        this@BaseActivity,
                        "Internet Connection will be disconnected",
                        MESSAGE
                    )
                }
            }
        }

    }

    private fun setActionBarUi() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar)
    }
}