package com.roshi.ufabertask.repowner

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.roshi.ufabertask.R
import com.roshi.ufabertask.model.Item

import kotlinx.android.synthetic.main.item_repo.view.*


class AllRepoAdapter(var context: Context, var listOfItem: ArrayList<Item>?) :
    RecyclerView.Adapter<AllRepoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfItem?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvName.text = listOfItem?.get(position)?.name
        holder.itemView.tvFullName.text=listOfItem?.get(position)?.fullName
        try {
            Glide.with(holder.itemView.context)
                .asBitmap()
                .apply(
                    RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.ic_placeholder).circleCrop()
                )
                .load(listOfItem?.get(position)?.owner?.avatarUrl)
                .into(object : BitmapImageViewTarget(holder.itemView.ivAvtar) {
                    override fun setResource(resource: Bitmap?) {
                        holder.itemView.ivAvtar.setImageBitmap(resource)
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}