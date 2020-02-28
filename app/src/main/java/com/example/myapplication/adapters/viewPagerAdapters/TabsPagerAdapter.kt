package com.example.myapplication.adapters.viewPagerAdapters

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.mvp.fragments.FavouritesFragment

class TabsPagerAdapter(private val context: Context, fm: FragmentManager, private var frags: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return frags[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return frags.size
    }

    companion object {
        @StringRes
        private val TAB_TITLES =
            intArrayOf(
                com.example.myapplication.R.string.tab_text_1,
                com.example.myapplication.R.string.tab_text_2
            )
    }
}