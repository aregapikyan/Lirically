package com.example.myapplication.managers.lyrics

import com.example.myapplication.*


interface LyricsManager {
    fun fetchLyricsApi(
        artist: String?,
        song: String?,
        c1: OnLyricsNotFoundListener,
        c2: OnLyricsViewModelsDataAddListener,
        c3: OnProgressEnabledListener,
        c4: OnBottomProgressEnabledListener,
        c5: OnErrorListener
    )

    fun fetchLyricsDb(
        artist: String?,
        song: String?,
        c1: OnLyricsViewModelsDataAddListener
    )
}