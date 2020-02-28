package com.example.myapplication.mvp.presenters

import android.content.Context
import com.example.myapplication.managers.lyrics.LyricsManager

class LyricsFavouritesPresenter(context: Context, manager: LyricsManager) :
    LyricsBasePresenter(context, manager) {
    fun fetchLyrics(artistName: String, songTitle: String) {
        manager.fetchLyricsDb(
            artistName,
            songTitle,
            onLyricsViewModelsDataAddListener
        )
    }
}