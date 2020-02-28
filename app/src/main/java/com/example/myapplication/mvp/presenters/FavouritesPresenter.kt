package com.example.myapplication.mvp.presenters

import android.content.Context
import com.example.myapplication.OnFavouritesEmptyListener
import com.example.myapplication.OnSongViewModelsDbDataAddListener
import com.example.myapplication.db.daos.FavouritesDao
import com.example.myapplication.db.database.AppDatabase
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.managers.hitSong.HitSongManager
import com.example.myapplication.mvp.base.AppPresenter
import com.example.myapplication.mvp.models.songModel.SongViewModel
import com.example.myapplication.mvp.views.FavouritesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouritesPresenter(context: Context, var manager: HitSongManager) : AppPresenter<FavouritesView>(context) {

    private val db = AppDatabase.getAppDataBase(context)
    private val favouritesDao = db?.favouritesDao()

    private val onSongViewModelsDbDataAdd: OnSongViewModelsDbDataAddListener = {
        view?.onSongViewModelsDataAdd(it)
    }

    private val onFavouritesEmpty: OnFavouritesEmptyListener = {
        view?.onFavouritesEmpty(it)
    }

    fun fetchSongs() {
        manager.fetchSongsDb(onSongViewModelsDbDataAdd, onFavouritesEmpty, onProgressEnabled, onBottomProgressEnabled, onError)
    }

    fun deleteFavourite(name: String?, title: String?) {
        GlobalScope.launch {
            favouritesDao?.deleteFavourite(name, title)
            view?.onFavouriteDeleted()
        }
    }

}