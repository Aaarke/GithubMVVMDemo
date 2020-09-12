package com.roshi.ufabertask.repowner

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.roshi.ufabertask.R
import com.roshi.ufabertask.model.Item
import com.roshi.ufabertask.utility.Keys
import kotlinx.android.synthetic.main.activity_contri_butor.toolbar
import kotlinx.android.synthetic.main.activity_repo_owner.*
import kotlinx.android.synthetic.main.content_owner_repo.*

class RepoOwnerActivity : AppCompatActivity() {
    private lateinit var repoOwnerViewModel: RepoOwnerViewModel
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_owner)
        repoOwnerViewModel = ViewModelProvider(this).get(RepoOwnerViewModel::class.java)
        setInitUi()
        setListOfRepo()

    }


    private fun setListOfRepo() {
        repoOwnerViewModel.getListOfRepos(item?.repoUrl!!)
        repoOwnerViewModel.listOfRepo.observe(this, {
            setAdapter(it)
        })
        repoOwnerViewModel.loadingVisibility.observe(this, {
            if (it == View.GONE) {
                pbRepoList.visibility = View.GONE
            } else {
                pbRepoList.visibility = View.VISIBLE
            }
        })
    }

    private fun setAdapter(list: ArrayList<Item>) {
        val mContriButorRepoAdapter = AllRepoAdapter(this, list)
        val mLinearLayoutManager =
            LinearLayoutManager(this)
        rvRepoList.layoutManager = mLinearLayoutManager
        rvRepoList.adapter = mContriButorRepoAdapter
    }

    private fun setInitUi() {
        val collapsingToolbar =
            findViewById<View>(R.id.toolbar_layout_contri) as CollapsingToolbarLayout
        collapsingToolbar.title = getString(R.string.repo_list)
        collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.material_black
            )
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val extras = intent.extras
        item = if (extras != null) {
            extras.getSerializable(Keys.EXTRAS.REPO_ITEM) as Item?
        } else {
            null
        }
        Glide.with(this)
            .load(item?.avtarUrl)
            .into(ivContriButor)

    }
}