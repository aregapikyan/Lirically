package com.example.myapplication.mvp.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.addidions.helpers.load
import com.example.myapplication.mvp.presenters.LyricsApiPresenter
import com.example.myapplication.managers.lyrics.LyricsManagerImpl
import com.example.myapplication.managers.lyrics.LyricsManager
import com.example.myapplication.managers.provider.Provider
import kotlinx.android.synthetic.main.activity_lyrics.*

class LyricsApiActivity : LyricsBaseActivity<LyricsApiPresenter>() {

    companion object {
        fun start(
            context: Context,
            artistName: String?,
            songTitle: String?,
            songArtImgUrl: String?,
            bundle: Bundle
        ) {
            start(context, artistName, songTitle, songArtImgUrl, bundle, LyricsApiActivity())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        getPresenter().fetchLyrics(artistName, songTitle)

        songArtistTV.text = artistName
        songTitleTV.text = songTitle

        toast = Toast(applicationContext)
        toastView = layoutInflater.inflate(R.layout.toast_custom_fav, findViewById(R.id.toastLayoutRoot))

        getPresenter().checkInFavourites(artistName, songTitle)

        songArtIV.load(songArtImgUrl)

        initClickEvents()
        initScrollEvents()
    }

    override fun createPresenter() = LyricsApiPresenter(this, Provider.getLyricsManager())

}