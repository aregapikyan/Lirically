package com.example.myapplication.mvp.activities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.myapplication.R.*
import com.example.myapplication.animation.scaleView
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.base.AppActivity
import com.example.myapplication.mvp.models.lyricsModel.LyricsViewModel
import com.example.myapplication.mvp.presenters.LyricsBasePresenter
import com.example.myapplication.mvp.views.LyricsView
import kotlinx.android.synthetic.main.activity_lyrics.*
import java.time.OffsetDateTime

abstract class LyricsBaseActivity<P : LyricsBasePresenter> : AppActivity<LyricsView, P>(),
    LyricsView {

    lateinit var toast: Toast
    lateinit var toastView: View

    private var objectAnimator = ObjectAnimator()

    lateinit var song: FavouritesEntry

    companion object {

        const val ARTIST_NAME_KEY = "ARTIST_NAME"
        const val SONG_TITLE_KEY = "SONG_TITLE"
        const val SONG_ART_URL = "SONG_ART_URL"

        fun start(
            context: Context,
            artistName: String?,
            songTitle: String?,
            songArtImgUrl: String?,
            bundle: Bundle,
            activity: Activity
        ) {
            val intent = Intent(context, activity::class.java)
            intent.putExtra(ARTIST_NAME_KEY, artistName)
            intent.putExtra(SONG_TITLE_KEY, songTitle)
            intent.putExtra(SONG_ART_URL, songArtImgUrl)
            context.startActivity(intent, bundle)
        }
    }

    val artistName: String by lazy {
        intent.getStringExtra(ARTIST_NAME_KEY) ?: ""
    }

    val songTitle: String by lazy {
        intent.getStringExtra(SONG_TITLE_KEY) ?: ""
    }

    val songArtImgUrl: String by lazy {
        intent.getStringExtra(SONG_ART_URL)
    }

    private val scrollSpeedCoefficient = 55

    override fun onAutoScrollPlay() {
        autoScrollFAB.setImageDrawable(getDrawable(drawable.ic_pause_black))
        objectAnimator = ObjectAnimator.ofInt(
            lyricsSV,
            "scrollY",
            lyricsSV.getChildAt(0).height
        )
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration =
            kotlin.math.abs((lyricsTV.bottom - lyricsCV.bottom) * scrollSpeedCoefficient)
                .toLong()
        println((lyricsTV.bottom - lyricsCV.bottom))
        println(((lyricsTV.bottom - lyricsCV.bottom)).toLong())
        objectAnimator.start()
    }

    override fun onAutoScrollStop() {
        autoScrollFAB.setImageResource(drawable.ic_play_arrow_black)
        objectAnimator.cancel()
    }

    override fun onLyricsViewModelsDataAdd(data: LyricsViewModel) {
        lyricsTV.text = data.lyrics
        favouritesBtn.isVisible = true
        createSong(data.lyrics)
    }

    override fun onLyricsNotFound(visible: Boolean) {
        favouritesBtn.isVisible = !visible
        lyricsEmptyTV.isVisible = visible
        lyricsTV.isVisible = !visible
    }

    @SuppressLint("CheckResult")
    override fun onFavouritesChecked() {
        scaleView(favouritesBtn, 1f, 1.1f, true)
        getPresenter().insertFavourite(song)
    }

    override fun onFavouriteInserted() {
        toast.duration = Toast.LENGTH_SHORT
        val coordinates = IntArray(2)
        lyricsCV.getLocationInWindow(coordinates)
        toast.setGravity(Gravity.TOP, 0, coordinates[1])
        toast.view = toastView
        toast.show()
    }


    @SuppressLint("CheckResult")
    override fun onFavouritesUnchecked() {
        scaleView(favouritesBtn, 1f, 0.9f, false)
        getPresenter().deleteFavourite(artistName, songTitle)
    }

    override fun onFavouriteExists() {
        scaleView(favouritesBtn, 1f, 1.1f, true)
        favouritesBtn.isChecked = true
    }

    override fun onBackPressed() {
        finish()
    }

    fun initClickEvents() {
        autoScrollFAB.setOnClickListener {
            getPresenter().onAutoScrollPressed()
        }

        lyricsNavBackBtn.setOnClickListener {
            onBackPressed()
        }

        lyricsTV.setOnTouchListener(object : View.OnTouchListener {
            private var gestureDetector = GestureDetector(
                this@LyricsBaseActivity,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onDoubleTap(e: MotionEvent?): Boolean {
                        favouritesBtn.isChecked = !favouritesBtn.isChecked
                        getPresenter().onFavouritesTriggered(favouritesBtn.isChecked)
                        return super.onDoubleTap(e)
                    }
                })

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                gestureDetector.onTouchEvent(event)
                return true
            }

        })

        favouritesBtn.setOnClickListener {
            favouritesBtn.isChecked = !favouritesBtn.isChecked
            getPresenter().onFavouritesTriggered(favouritesBtn.isChecked)
        }
    }

    fun createSong(lyrics: String?): FavouritesEntry {
        song = FavouritesEntry(
            (artistName + songTitle).hashCode(),
            artistName,
            songTitle,
            songArtImgUrl,
            lyrics,
            OffsetDateTime.now()
        )

        return song
    }

    fun initScrollEvents() {
        lyricsSV.setOnTouchListener { _, event ->
            getPresenter().onScrollViewScrolled(event, objectAnimator)
            return@setOnTouchListener false
        }
    }


    override fun onFavouriteDeleted() {

    }

    override fun onFavouriteGot(favourite: FavouritesEntry?) {

    }

    override fun onFavouriteUpdated() {

    }

    override fun onFavouritesGot() {

    }
}