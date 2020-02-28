package com.example.myapplication.mvp.views

import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.base.AppView
import com.example.myapplication.mvp.models.songModel.SongViewModel

interface FavouritesView : AppView {
    fun onSongViewModelsDataAdd(data: ArrayList<SongViewModel>)
    fun onFavouritesEmpty(visible: Boolean)
    fun onFavouriteInserted()
    fun onFavouriteUpdated()
    fun onFavouriteDeleted()
    fun onFavouriteGot(favourite: FavouritesEntry?)
    fun onFavouritesGot()
}