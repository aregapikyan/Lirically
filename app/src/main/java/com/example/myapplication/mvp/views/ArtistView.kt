package com.example.myapplication.mvp.views

import com.example.myapplication.mvp.base.AppView
import com.example.myapplication.mvp.models.songModel.SongViewModel

interface ArtistView : AppView {
    fun onSongViewModelsDataAdd(data: ArrayList<SongViewModel>)
}