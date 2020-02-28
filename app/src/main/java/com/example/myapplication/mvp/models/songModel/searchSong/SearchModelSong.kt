package com.example.myapplication.mvp.models.songModel.searchSong

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SearchModelSong {
    @SerializedName("response")
    @Expose
    var response: SearchResponseModelSong? = null
}