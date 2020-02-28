package com.example.myapplication.mvp.models.songModel

import com.example.myapplication.mvp.models.songModel.searchSong.Song

class SongViewModel {

    companion object {
        fun songModelConverter(song: Song): SongViewModel {
            val songViewModel = SongViewModel()
            songViewModel.title = song.title
            songViewModel.songArtImageUrl = song.songArtImageUrl
            songViewModel.name = song.primaryArtist?.name
            return songViewModel
        }
    }

    var name: String? = null
    var title: String? = null
    var songArtImageUrl: String? = null
}