package com.example.myapplication.mvp.presenters

import android.animation.ObjectAnimator
import android.content.Context
import android.view.MotionEvent
import com.example.myapplication.OnLyricsNotFoundListener
import com.example.myapplication.OnLyricsViewModelsDataAddListener
import com.example.myapplication.db.database.AppDatabase
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.base.AppPresenter
import com.example.myapplication.mvp.views.LyricsView
import com.example.myapplication.managers.lyrics.LyricsManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class LyricsBasePresenter(var context: Context, var manager: LyricsManager) : AppPresenter<LyricsView>(context) {

    val db = AppDatabase.getAppDataBase(context)
    val favouritesDao = db?.favouritesDao()

    private var isPlaying = false

    var onLyricsViewModelsDataAddListener: OnLyricsViewModelsDataAddListener = {
        view?.onLyricsViewModelsDataAdd(it)
    }

    var onLyricsNotFoundListener: OnLyricsNotFoundListener = {
        view?.onLyricsNotFound(it)
    }

    fun onAutoScrollPressed() {
        if (isPlaying) {
            view?.onAutoScrollStop()
        } else {
            view?.onAutoScrollPlay()
        }

        isPlaying = !isPlaying
    }

    fun onScrollViewScrolled(event: MotionEvent, objectAnimator: ObjectAnimator) {
        if (isPlaying) {
            if (event.action == MotionEvent.ACTION_UP) {
                view?.onAutoScrollPlay()
            } else if (!objectAnimator.isPaused) {
                view?.onAutoScrollStop()
            }
        }
    }

    fun onFavouritesTriggered(checked: Boolean) {
        if (checked) {
            view?.onFavouritesChecked()
        } else {
            view?.onFavouritesUnchecked()
        }
    }

    fun checkInFavourites(artistName: String, songTitle: String) {

        GlobalScope.launch {
            val song = favouritesDao?.getFavourite(artistName, songTitle)

            if (song != null) {
                view?.onFavouriteExists()
            }

        }

    }

    fun insertFavourite(favouritesEntry: FavouritesEntry) {
        GlobalScope.launch {
            favouritesDao?.insertFavourite(favouritesEntry)
            view?.onFavouriteInserted()
        }
    }

    fun deleteFavourite(name: String?, title: String?) {
        GlobalScope.launch {
            favouritesDao?.deleteFavourite(name, title)
            view?.onFavouriteDeleted()
        }
    }

}