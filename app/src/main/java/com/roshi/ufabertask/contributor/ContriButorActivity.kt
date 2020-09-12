package com.roshi.ufabertask.contributor

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.roshi.ufabertask.R

class ContriButorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contri_butor)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

    }

    override fun onStart() {
        super.onStart()
        setInitialUI()
        setValueInUi()
        getListOfContriButer()
    }

    private fun getListOfContriButer() {

    }

    private fun setValueInUi() {

    }

    private fun setInitialUI() {

    }

}