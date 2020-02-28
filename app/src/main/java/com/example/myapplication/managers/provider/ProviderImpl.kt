package com.example.myapplication.managers.provider

import android.app.Activity
import android.content.Context
import com.example.myapplication.managers.hitSong.HitSongManagerImpl
import com.example.myapplication.managers.lyrics.LyricsManagerImpl

class ProviderImpl(private val context: Context) : Provider() {
    override fun getHitSongManager(activity: Activity) = HitSongManagerImpl(context, activity)

    override fun getLyricsManager() = LyricsManagerImpl(context)
}