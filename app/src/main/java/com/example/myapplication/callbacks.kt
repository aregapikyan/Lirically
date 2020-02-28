package com.example.myapplication

import com.example.myapplication.mvp.models.hitModel.HitViewModel
import com.example.myapplication.mvp.models.lyricsModel.LyricsViewModel
import com.example.myapplication.mvp.models.songModel.SongViewModel

//Base manager callbacks

typealias OnProgressEnabledListener = (enabled: Boolean) -> Unit
typealias OnBottomProgressEnabledListener = (enabled: Boolean) -> Unit
typealias OnErrorListener = (message: String) -> Unit

//Artist callbacks

typealias OnSongViewModelsDataAddListener = (songViewModels: ArrayList<SongViewModel>) -> Unit

//Search callbacks

typealias OnHitViewModelsDataAddListener = (hitViewModels: ArrayList<HitViewModel>) -> Unit
typealias OnHitViewModelsDataReplaceListener = (hitViewModels: ArrayList<HitViewModel>) -> Unit

//Favourites callbacks

typealias OnSongViewModelsDbDataAddListener = (songViewModels: ArrayList<SongViewModel>) -> Unit
typealias OnFavouritesEmptyListener = (visible: Boolean) -> Unit

//Lyrics callbacks

typealias OnLyricsNotFoundListener = (visible: Boolean) -> Unit
typealias OnLyricsViewModelsDataAddListener = (lyricsViewModel: LyricsViewModel) -> Unit