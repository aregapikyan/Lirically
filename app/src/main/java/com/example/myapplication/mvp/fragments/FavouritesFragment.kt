package com.example.myapplication.mvp.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter.OnItemClickListener
import com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter.SongRecyclerAdapter
import com.example.myapplication.addidions.helpers.RecyclerItemTouchHelper
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.managers.provider.Provider
import com.example.myapplication.mvp.activities.LyricsFavouritesActivity
import com.example.myapplication.mvp.base.AppFragment
import com.example.myapplication.mvp.models.songModel.SongViewModel
import com.example.myapplication.mvp.presenters.FavouritesPresenter
import com.example.myapplication.mvp.views.FavouritesView
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.view_item_song.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class FavouritesFragment : AppFragment<FavouritesView, FavouritesPresenter>(), FavouritesView,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private val layoutManager by lazy {
        LinearLayoutManager(context)
    }

    lateinit var context: Activity

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    override val layoutId = com.example.myapplication.R.layout.fragment_favourites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context = activity!!

        initRecyclerView()

        getPresenter().fetchSongs()
    }

    override fun onStart() {
        super.onStart()
        getPresenter().fetchSongs()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is SongRecyclerAdapter.Companion.ViewHolder) {
            // remove the item from recycler view

            getPresenter().deleteFavourite(
                viewHolder.songViewModel.name,
                viewHolder.songViewModel.title
            )

        }
    }

    override fun onFavouriteDeleted() {
        getPresenter().fetchSongs()

    }

    private val adapter by lazy {
        SongRecyclerAdapter(
            context,
            object :
                OnItemClickListener {
                override fun onItemClick(viewModel: SongViewModel, itemView: View) {

                    LyricsFavouritesActivity.start(
                        activity!!,
                        viewModel.name,
                        viewModel.title,
                        viewModel.songArtImageUrl,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity!!,
                            itemView.songArtIV,
                            ViewCompat.getTransitionName(itemView.songArtIV)!!
                        ).toBundle()!!
                    )
                }
            })
    }

    private fun initRecyclerView() {
        favouritesSongRV.adapter = adapter
        favouritesSongRV.layoutManager = layoutManager
        favouritesSongRV.itemAnimator = DefaultItemAnimator()

        val itemTouchHelperCallback =
            RecyclerItemTouchHelper(
                0,
                ItemTouchHelper.LEFT,
                this
            )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(favouritesSongRV)
    }

    override fun createPresenter() = FavouritesPresenter(
        activity!!, Provider.getHitSongManager(activity!!)
    )


    override fun onFavouritesEmpty(visible: Boolean) {
        MainScope().launch {
            favouritesSongRV.isVisible = !visible
            noFavouritesTV.isVisible = visible
        }
    }

    override fun onSongViewModelsDataAdd(data: ArrayList<SongViewModel>) {
        MainScope().launch {
            adapter.replaceSongViewModels(data)
        }
    }

    override fun onFavouriteInserted() {

    }

    override fun onFavouriteUpdated() {

    }

    override fun onFavouriteGot(favourite: FavouritesEntry?) {

    }

    override fun onFavouritesGot() {

    }

}