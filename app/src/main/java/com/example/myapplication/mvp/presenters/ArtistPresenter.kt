package com.example.myapplication.mvp.presenters

import android.content.Context
import com.example.myapplication.OnSongViewModelsDataAddListener
import com.example.myapplication.mvp.base.AppPresenter
import com.example.myapplication.mvp.views.ArtistView
import com.example.myapplication.managers.hitSong.HitSongManager

//
class ArtistPresenter(context: Context, var artistId: Int, var manager: HitSongManager) : AppPresenter<ArtistView>(context) {

    private val onSongViewModelsDataAdd: OnSongViewModelsDataAddListener = {
        view?.onSongViewModelsDataAdd(it)
    }

    fun fetchSongs(id: Int?) {
        manager.fetchSongsApi(id, onSongViewModelsDataAdd, onProgressEnabled, onBottomProgressEnabled, onError)
    }

    fun onRecyclerViewScrolled(totalItemCount: Int, lastVisibleItemPosition: Int) {
        val isLastItem = lastVisibleItemPosition == totalItemCount - 1

        if (isLastItem) {
            fetchSongs(artistId)
        }
    }




}