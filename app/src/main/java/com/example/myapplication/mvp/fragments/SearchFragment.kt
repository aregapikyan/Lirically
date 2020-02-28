package com.example.myapplication.mvp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.recyclerViewAdapters.HitRecyclerAdapter.HitRecyclerAdapter
import com.example.myapplication.listeners.OnArtistImageClickListener
import com.example.myapplication.listeners.OnSongArtClickListener
import com.example.myapplication.managers.hitSong.HitSongManager
import com.example.myapplication.managers.hitSong.HitSongManagerImpl
import com.example.myapplication.managers.provider.Provider
import com.example.myapplication.mvp.base.AppFragment
import com.example.myapplication.mvp.models.hitModel.HitViewModel
import com.example.myapplication.mvp.presenters.SearchPresenter
import com.example.myapplication.mvp.views.SearchView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_search.*
import java.util.concurrent.TimeUnit

class SearchFragment : AppFragment<SearchView, SearchPresenter>(), SearchView {

    companion object {
        fun newInstance() = SearchFragment()
    }

    val layoutManager by lazy {
        LinearLayoutManager(this.activity)
    }

    override val layoutId = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPresenter().getPreferences()

        initRecyclerAdapters()
        initClickEvents()
        initTouchEvents()
        initEditTextEvents()
        initRecyclerViewScrollListener()

    }

    private val adapter by lazy {
        HitRecyclerAdapter(
            this.activity!!,
            OnSongArtClickListener(activity!!),
            OnArtistImageClickListener(activity!!)
        )
    }

    override fun createPresenter(): SearchPresenter =
        SearchPresenter(
            activity!!, Provider.getHitSongManager(activity!!)
        )

    override fun onHitViewModelsDataAdd(data: ArrayList<HitViewModel>) {
        this.adapter.addHitViewModels(data)
    }

    override fun onHitViewModelsDataReplace(data: ArrayList<HitViewModel>) {
        this.adapter.replaceHitViewModels(data)
    }

    override fun onBottomProgressEnabled(enabled: Boolean) {
        searchBottomPB.isVisible = enabled
    }

    override fun onCancelButtonEnabled(enabled: Boolean) {
        cancelBtn.isVisible = enabled
    }

    private fun initEditTextEvents() {
        initSearch()
        initWatcherEvents()
    }

    override fun onPreferencesGot(lastSearch: String) {
        searchET.setText(lastSearch)
        getPresenter().fetchHits(lastSearch)
    }

    @SuppressLint("CheckResult")
    private fun initSearch() {
        RxTextView.textChanges(searchET)
            .skip(1)
            .map { it.toString() }
            .debounce(
                1000
                , TimeUnit.MILLISECONDS
            )
            .retry()
            .subscribe {
                getPresenter().fetchHits(
                    it
                )
            }
    }

    private fun initRecyclerViewScrollListener() {
        searchResultRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                getPresenter().onRecyclerViewScrolled(
                    layoutManager.itemCount,
                    layoutManager.findLastCompletelyVisibleItemPosition(),
                    activity!!
                )

            }
        })
    }

    private fun initClickEvents() {
        cancelBtn.setOnClickListener {
            searchET.text = null
            cancelBtn.isVisible = false
        }
    }

    private fun initTouchEvents() {
        searchResultRV.setOnTouchListener { view: View, _: MotionEvent ->

            (this@SearchFragment.activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken,
                0
            )

            return@setOnTouchListener false
        }
    }

    private fun initWatcherEvents() {
        searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
//                getPresenter().onSearchTextChanged(searchET.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getPresenter().onSearchTextChanged(searchET.text.toString())
            }
        })
    }

    private fun initRecyclerAdapters() {
        searchResultRV.adapter = adapter
        searchResultRV.layoutManager = layoutManager
        searchResultRV.addItemDecoration(
            DividerItemDecoration(
                this@SearchFragment.activity!!,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}
