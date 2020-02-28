package com.example.myapplication.mvp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.viewPagerAdapters.TabsPagerAdapter
import com.example.myapplication.mvp.fragments.FavouritesFragment
import com.example.myapplication.mvp.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tabsPagerAdapter =
            TabsPagerAdapter(
                this,
                supportFragmentManager,
                arrayListOf(SearchFragment.newInstance(), FavouritesFragment.newInstance())
            )

        viewPager.adapter = tabsPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

    }

}