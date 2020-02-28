package com.example.myapplication.mvp.base

interface AppView {
    fun onError(message: String)
    fun onProgressEnabled(enabled: Boolean)
    fun onBottomProgressEnabled(enabled: Boolean)
}