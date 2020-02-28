package com.example.myapplication.managers.provider

import android.app.Activity
import android.content.Context
import com.example.myapplication.managers.hitSong.HitSongManager
import com.example.myapplication.managers.lyrics.LyricsManager

abstract class Provider {

    companion object : Provider(){
        override fun getHitSongManager(activity: Activity) = provider.getHitSongManager(activity)

        lateinit var provider: Provider

        fun init(context: Context) {
            provider = ProviderImpl(context)
        }

        override fun getLyricsManager() = provider.getLyricsManager()
    }

    abstract fun getLyricsManager(): LyricsManager

    abstract fun getHitSongManager(activity: Activity): HitSongManager
}

