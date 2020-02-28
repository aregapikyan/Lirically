package com.example.myapplication.mvp.models.lyricsModel

class LyricsViewModel {

    companion object {
        fun lyricsModelConverter(lyrics : String?): LyricsViewModel {
            val lyricsViewModel = LyricsViewModel()

            lyricsViewModel.lyrics = lyrics

            return lyricsViewModel
        }
    }

    var lyrics : String? = null

}