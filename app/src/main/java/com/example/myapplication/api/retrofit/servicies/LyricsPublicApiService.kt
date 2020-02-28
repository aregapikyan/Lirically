package com.example.myapplication.api.retrofit.servicies

import com.example.myapplication.mvp.models.lyricsModel.searchLyrics.SearchModelLyrics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsPublicApiService {
    @GET("{artist}/{song}")
    fun searchLyrics(@Path("artist") artist: String?, @Path("song") song : String?) : Call<SearchModelLyrics>
}