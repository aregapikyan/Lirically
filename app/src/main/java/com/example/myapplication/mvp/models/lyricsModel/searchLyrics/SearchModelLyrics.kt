package com.example.myapplication.mvp.models.lyricsModel.searchLyrics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SearchModelLyrics {

    @SerializedName("lyrics")
    @Expose
    var lyrics: String? = null

}