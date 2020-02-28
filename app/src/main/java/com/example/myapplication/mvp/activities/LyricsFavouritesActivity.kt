package com.example.myapplication.mvp.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.example.myapplication.addidions.helpers.load
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.presenters.LyricsFavouritesPresenter
import com.example.myapplication.managers.lyrics.LyricsManagerImpl
import com.example.myapplication.managers.lyrics.LyricsManager
import com.example.myapplication.managers.provider.Provider
import kotlinx.android.synthetic.main.activity_lyrics.*

class LyricsFavouritesActivity : LyricsBaseActivity<LyricsFavouritesPresenter>() {

    companion object {
        fun start(
            context: Context,
            artistName: String?,
            songTitle: String?,
            songArtImgUrl: String?,
            bundle: Bundle
        ) {
            start(context, artistName, songTitle, songArtImgUrl, bundle, LyricsFavouritesActivity())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        songArtistTV.text = artistName
        songTitleTV.text = songTitle

        getPresenter().fetchLyrics(artistName, songTitle)

        favouritesBtn.isVisible = true

        toast = Toast(applicationContext)

        toastView =
            layoutInflater.inflate(R.layout.toast_custom_fav, findViewById(R.id.toastLayoutRoot))

        songArtIV.load(songArtImgUrl)

        getPresenter().checkInFavourites(artistName, songTitle)

        initClickEvents()
        initScrollEvents()
    }

    override fun onFavouriteGot(favourite: FavouritesEntry?) {
        lyricsTV.text = favourite?.lyrics
        createSong(favourite?.lyrics)
    }

    override fun createPresenter() = LyricsFavouritesPresenter(this, Provider.getLyricsManager())

}