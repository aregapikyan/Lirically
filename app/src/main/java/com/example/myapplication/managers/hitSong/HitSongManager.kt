package com.example.myapplication.managers.hitSong

import android.content.Context
import com.example.myapplication.*


interface HitSongManager {
    //    todo rename names
    fun fetchHits(
        text: String,
        OnHitViewModelsDataReplace: OnHitViewModelsDataReplaceListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener
    )

    fun fetchSongsApi(
        id: Int?,
        onSongViewModelsDataAdd: OnSongViewModelsDataAddListener,
        onProgress: OnProgressEnabledListener,
        onBottomProgress: OnBottomProgressEnabledListener,
        onError: OnErrorListener
    )

    fun fetchSongsDb(
        OnSongViewModelsDbDataAdd: OnSongViewModelsDbDataAddListener,
        OnFavouritesEmpty: OnFavouritesEmptyListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener
    )

    fun getNextPageResults(
        context: Context,
        OnHitViewModelsDataAdd: OnHitViewModelsDataAddListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener

    )

}