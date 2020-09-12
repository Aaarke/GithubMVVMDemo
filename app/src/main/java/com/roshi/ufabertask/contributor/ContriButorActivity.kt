package com.roshi.ufabertask.contributor

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.roshi.ufabertask.R
import com.roshi.ufabertask.model.GitData
import com.roshi.ufabertask.model.Item
import com.roshi.ufabertask.utility.Keys
import kotlinx.android.synthetic.main.activity_contri_butor.*
import kotlinx.android.synthetic.main.content_repo_contri.*
import java.util.*

class ContriButorActivity : AppCompatActivity() {
    private lateinit var contriButorViewModel: ContriButorViewModel
    private var item: GitData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contri_butor)
        contriButorViewModel = ViewModelProvider(this).get(ContriButorViewModel::class.java)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        setValueInUi()
        setInitialUI()

        getListOfContriButor()
    }

    private fun getListOfContriButor() {
        contriButorViewModel.getAllContriButerList(item?.contributorsUrl!!)
        contriButorViewModel.contriButerListItem.observe(this, {
            setAdapter(it)
        })

        contriButorViewModel.loadingVisibility.observe(this, {
            if (it == View.GONE) {
                pbContriButorLoader.visibility = View.GONE
            } else {
                pbContriButorLoader.visibility = View.VISIBLE

            }
        })
    }

    private fun setAdapter(items: ArrayList<Item>?) {
        val mContributorAdapter = ContributorAdapter(this, items,object:
            OnRepoItemClickedListener {
            override fun onItemClicked(pos:Int,item: Item,imageView: ImageView) {
                val bundle = Bundle()
                val i = Intent(this@ContriButorActivity, ContriButorActivity::class.java)
                bundle.putSerializable(Keys.EXTRAS.REPO_ITEM, item)
                i.putExtras(bundle)
                startActivity(i)
            }
        })
        val mGridLayoutManager = GridLayoutManager(this,4)
        mGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                mGridLayoutManager.spanCount
                return 1
            }
        }
        rvContriButor.layoutManager = mGridLayoutManager
        rvContriButor.adapter = mContributorAdapter
    }

    private fun setValueInUi() {
        val extras = intent.extras

        item = if (extras != null) {
            extras.getSerializable(Keys.EXTRAS.REPO_ITEM) as GitData?
        } else {
            null
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName =
                extras?.getString(Keys.EXTRAS.EXTRA_AVTAR_IMAGE_TRANSITION_NAME)
            ivAuthor.transitionName = imageTransitionName
        }

        tvRepoDescriptionValue.text = item?.description
        Glide.with(this)
            .asBitmap()
            .load(item?.owner?.avatarUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    ivAuthor.setImageBitmap(resource)
                    setToolbarColor(resource)
                }
            })
    }

    fun setToolbarColor(bitmap: Bitmap) {
        // Generate the palette and get the vibrant swatch
        val vibrantSwatch = createPaletteSync(bitmap).vibrantSwatch
        // Set the toolbar background and text colors.
        // Fall back to default colors if the vibrant swatch is not available.
        with(findViewById<AppBarLayout>(R.id.app_bar)) {
            setBackgroundColor(
                vibrantSwatch?.rgb ?: ContextCompat.getColor(context, R.color.material_grey300)
            )
        }
    }

    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()


    private fun setInitialUI() {
        val collapsingToolbar = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        collapsingToolbar.title = item?.name
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
    }

}