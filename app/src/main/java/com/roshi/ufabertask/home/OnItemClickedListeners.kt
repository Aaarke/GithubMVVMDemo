package com.roshi.ufabertask.home

import android.widget.ImageView
import com.roshi.ufabertask.model.GitData
import java.text.FieldPosition

interface OnItemClickedListeners {
    fun onItemClicked(gitData: GitData,position: Int,imageView: ImageView)
}