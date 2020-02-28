package com.example.myapplication.mvp.models.songModel.searchSong

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponseModelSong {

    @SerializedName("songs")
    @Expose
    var songs: List<Song>? = null

    @SerializedName("next_page")
    @Expose
    var nextPage: Int? = null
}