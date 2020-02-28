package com.example.myapplication.mvp.views

import com.example.myapplication.mvp.base.AppView
import com.example.myapplication.mvp.models.hitModel.HitViewModel

interface SearchView: AppView {
    fun onHitViewModelsDataAdd(data: ArrayList<HitViewModel>)
    fun onHitViewModelsDataReplace(data: ArrayList<HitViewModel>)
    fun onCancelButtonEnabled(enabled: Boolean)
    fun onPreferencesGot(lastSearch: String)
}