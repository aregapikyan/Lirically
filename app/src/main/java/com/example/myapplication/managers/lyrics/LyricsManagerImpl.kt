package com.example.myapplication.managers.lyrics

import android.content.Context
import com.example.myapplication.*
import com.example.myapplication.api.retrofit.apis.LyricsPublicApi
import com.example.myapplication.managers.base.BaseManagerImpl
import com.example.myapplication.mvp.DefaultCallback
import com.example.myapplication.mvp.models.lyricsModel.LyricsViewModel
import com.example.myapplication.mvp.models.lyricsModel.searchLyrics.SearchModelLyrics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LyricsManagerImpl(var context: Context) : LyricsManager, BaseManagerImpl(context) {

    override fun fetchLyricsDb(
        artist: String?,
        song: String?,
        c1: OnLyricsViewModelsDataAddListener
    ) {
        GlobalScope.launch {
            val lyrics = favouritesDao?.getFavourite(artist, song)?.lyrics

            c1.invoke(LyricsViewModel.lyricsModelConverter(lyrics))
        }
    }

    override fun fetchLyricsApi(
        artist: String?,
        song: String?,
        c1: OnLyricsNotFoundListener,
        c2: OnLyricsViewModelsDataAddListener,
        c3: OnProgressEnabledListener,
        c4: OnBottomProgressEnabledListener,
        c5: OnErrorListener
    ) {
        requestCall(
            LyricsPublicApi.publicApi.service.searchLyrics(artist, song),
            object : DefaultCallback<SearchModelLyrics>() {
                override fun onResponse(
                    call: Call<SearchModelLyrics>,
                    response: Response<SearchModelLyrics>
                ) {
                    val lyricsViewModel =
                        LyricsViewModel.lyricsModelConverter(response.body()?.lyrics)
                    if (lyricsViewModel.lyrics.isNullOrEmpty()) {
                        c1.invoke(true)
                    } else {
                        c2.invoke(lyricsViewModel)
                        c1.invoke(false)
                    }
                }
            },
            1,
            c3,
            c4,
            c5
        )
    }

}