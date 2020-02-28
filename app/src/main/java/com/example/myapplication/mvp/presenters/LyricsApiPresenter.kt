package com.example.myapplication.mvp.presenters

import android.content.Context
import com.example.myapplication.OnLyricsNotFoundListener
import com.example.myapplication.OnLyricsViewModelsDataAddListener
import com.example.myapplication.managers.lyrics.LyricsManager

class LyricsApiPresenter(context: Context, manager: LyricsManager) :
    LyricsBasePresenter(context, manager) {

    fun fetchLyrics(artist: String?, song: String?) {
        manager.fetchLyricsApi(
            artist,
            song,
            onLyricsNotFoundListener,
            onLyricsViewModelsDataAddListener,
            onProgressEnabled,
            onBottomProgressEnabled,
            onError
        )
    }

}