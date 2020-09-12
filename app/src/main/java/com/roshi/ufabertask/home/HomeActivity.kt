package com.roshi.ufabertask.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.roshi.ufabertask.R
import com.roshi.ufabertask.base.BaseActivity
import com.roshi.ufabertask.contributor.ContriButorActivity
import com.roshi.ufabertask.model.GitData
import com.roshi.ufabertask.utility.Keys
import com.roshi.ufabertask.utility.Keys.EXTRAS.Companion.EXTRA_AVTAR_IMAGE_TRANSITION_NAME

class HomeActivity : BaseActivity() ,HomeFragment.OnHomeFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }



    override fun onFragmentInteraction(position: Int, item: GitData, imageView: ImageView) {
       openContriButerActivity(position,item,imageView)
    }

    private fun openContriButerActivity(position: Int, item: GitData, sharedImageView: ImageView) {
        val bundle = Bundle()
        val i = Intent(this, ContriButorActivity::class.java)
        bundle.putSerializable(Keys.EXTRAS.REPO_ITEM, item)
        i.putExtras(bundle)
        i.putExtra(EXTRA_AVTAR_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            sharedImageView,
            ViewCompat.getTransitionName(sharedImageView)!!
        )
        startActivity(i, options.toBundle())
    }
}