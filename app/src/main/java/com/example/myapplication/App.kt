package com.example.myapplication

import android.app.Activity
import android.app.Application
import com.example.myapplication.managers.provider.Provider


class App : Application() {

    override fun onCreate() {

        super.onCreate()
//        FontsOverride.setDefaultFont(this, "DEFAULT", "fff_tusj.ttf")
//        FontsOverride.setDefaultFont(this, "MONOSPACE", "fff_tusj.ttf")
//        FontsOverride.setDefaultFont(this, "SERIF", "fff_tusj.ttf")
//        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fff_tusj.ttf")


        Provider.init(this)
    }

}