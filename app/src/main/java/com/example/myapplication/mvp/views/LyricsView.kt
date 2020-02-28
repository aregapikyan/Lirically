package com.example.myapplication.mvp.views

import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.base.AppView
import com.example.myapplication.mvp.models.lyricsModel.LyricsViewModel

interface LyricsView : AppView {
    fun onLyricsViewModelsDataAdd(data: LyricsViewModel)
    fun onAutoScrollStop()
    fun onAutoScrollPlay()
    fun onLyricsNotFound(visible: Boolean)
    fun onFavouritesChecked()
    fun onFavouritesUnchecked()
    fun onFavouriteExists()
    fun onFavouriteInserted()
    fun onFavouriteUpdated()
    fun onFavouriteDeleted()
    fun onFavouriteGot(favourite: FavouritesEntry?)
    fun onFavouritesGot()
}