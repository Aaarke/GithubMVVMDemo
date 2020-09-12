package com.roshi.ufabertask.contributor

import android.widget.ImageView
import com.roshi.ufabertask.model.Item

interface OnRepoItemClickedListener {
    fun onItemClicked(position:Int, item: Item, imageView: ImageView)
}