package com.roshi.ufabertask.home

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.roshi.ufabertask.R
import com.roshi.ufabertask.databinding.ItemRepoBinding
import com.roshi.ufabertask.model.GitData
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter(private var inflater: LayoutInflater, private var onItemClickedListeners: OnItemClickedListeners) :
    ListAdapter<GitData, RepoAdapter.ViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(inflater, R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
        holder.itemView.setOnClickListener {
            onItemClickedListeners.onItemClicked(getItem(position),position,holder.itemView.ivAvtar)
        }
        ViewCompat.setTransitionName(holder.itemView.ivAvtar,getItem(position)?.htmlUrl)


    }

   inner class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            try {
                Glide.with(binding.root.context)
                    .asBitmap()
                    .apply(
                        RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.ic_placeholder).circleCrop()
                    )
                    .load(getItem(position)?.owner?.avatarUrl)
                    .into(object : BitmapImageViewTarget(binding.ivAvtar) {
                        override fun setResource(resource: Bitmap?) {
                            binding.ivAvtar.setImageBitmap(resource)
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }

            binding.tvFullName.text = getItem(position).fullName
            binding.tvName.text = getItem(position).name
        }


    }


    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GitData> =
            object : DiffUtil.ItemCallback<GitData>() {
                override fun areItemsTheSame(oldUser: GitData, newUser: GitData): Boolean {
                    return oldUser.id == newUser.id
                }

                override fun areContentsTheSame(
                    oldUser: GitData, newUser: GitData
                ): Boolean {
                    return false
                }
            }
    }



}