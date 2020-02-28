package com.example.myapplication.mvp.presenters

import android.app.Activity
import android.content.Context
import com.example.myapplication.OnHitViewModelsDataAddListener
import com.example.myapplication.OnHitViewModelsDataReplaceListener
import com.example.myapplication.managers.hitSong.HitSongManager
import com.example.myapplication.mvp.base.AppPresenter
import com.example.myapplication.mvp.views.SearchView


class SearchPresenter(var context: Context, var manager: HitSongManager) :
    AppPresenter<SearchView>(context) {

    private val SAVED_SEARCH by lazy {
        "SAVED_SEARCH"
    }

    private val DEFAULT_SEARCH_VALUE by lazy {
        ""
    }

    private val sharedPrefs by lazy {
        (context as Activity).getPreferences(Context.MODE_PRIVATE)
    }

    var onHitViewModelsDataAdd: OnHitViewModelsDataAddListener = {
        view?.onHitViewModelsDataAdd(it)
    }

    var onHitViewModelsDataReplace: OnHitViewModelsDataReplaceListener = {
        view?.onHitViewModelsDataReplace(it)
    }

    fun fetchHits(
        text: String
    ) {
        manager.fetchHits(text, onHitViewModelsDataReplace, onProgressEnabled, onBottomProgressEnabled, onError)
    }

    private fun getNextPageResults(context: Context) {
        manager.getNextPageResults(context, onHitViewModelsDataAdd, onProgressEnabled, onBottomProgressEnabled, onError)
    }

    fun onSearchTextChanged(text: String) {
        view?.onCancelButtonEnabled(text.isNotEmpty())
    }

    fun onRecyclerViewScrolled(
        totalItemCount: Int,
        lastVisibleItemPosition: Int,
        context: Context
    ) {
        val isLastItem = lastVisibleItemPosition == totalItemCount - 1

        if (isLastItem) {
            getNextPageResults(context)
        }
    }

    fun getPreferences() {
        val lastSearch = sharedPrefs.getString(
            SAVED_SEARCH,
            DEFAULT_SEARCH_VALUE
        ) ?: ""

        view?.onPreferencesGot(lastSearch)
    }

}