package com.roshi.ufabertask.utility

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.roshi.ufabertask.R

object Utils {
    fun snackBar(activity: Activity, message: String?, type: Int) {
        try {
            snackBar(
                activity,
                message,
                activity.window.decorView.findViewById(android.R.id.content),
                type
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun snackBar(activity: Activity?, message: String?, view: View?, type: Int) {
        if (activity == null) return
        activity.runOnUiThread(Runnable {
            try {
                val snackbar =
                    Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG).setDuration(2500)
                val view1 = snackbar.view
                val tv =
                    view1.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                tv.maxLines = 3
                tv.gravity = Gravity.CENTER_HORIZONTAL
                tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
                tv.setTextColor(ContextCompat.getColor(activity, R.color.white))
                view1.setBackgroundColor(
                    ContextCompat.getColor(
                        activity,
                        if (type == Codes.SnackBarType.SUCCESS) R.color.snackbar_bg_color_success else R.color.snackbar_bg_color_error
                    )
                )
                snackbar.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
}