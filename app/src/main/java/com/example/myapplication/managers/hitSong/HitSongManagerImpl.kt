package com.example.myapplication.managers.hitSong

import android.app.Activity
import android.content.Context
import com.example.myapplication.*
import com.example.myapplication.api.retrofit.apis.PublicApi
import com.example.myapplication.managers.base.BaseManagerImpl
import com.example.myapplication.mvp.DefaultCallback
import com.example.myapplication.mvp.models.hitModel.HitViewModel
import com.example.myapplication.mvp.models.hitModel.searchHit.SearchModelHit
import com.example.myapplication.mvp.models.songModel.SongViewModel
import com.example.myapplication.mvp.models.songModel.searchSong.SearchModelSong
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HitSongManagerImpl(
    var context: Context,
    var activity: Activity
) : HitSongManager, BaseManagerImpl(context) {

    private var page: Int? = 1
    private var mPage: Int? = 1

    private var lastSearch = String()

    private val sharedPrefs by lazy {
        (activity).getPreferences(Context.MODE_PRIVATE)
    }

    override fun fetchHits(
        text: String,
        OnHitViewModelsDataReplace: OnHitViewModelsDataReplaceListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener
    ) {
        if (text.isNotEmpty()) {
            requestCall(
                PublicApi.publicApi.service.search(text, mPage),
                object : DefaultCallback<SearchModelHit>() {
                    override fun onResponse(
                        call: Call<SearchModelHit>,
                        response: Response<SearchModelHit>
                    ) {
                        createModels(response) {
                            OnHitViewModelsDataReplace.invoke(it)

                            with(sharedPrefs.edit()) {
                                putString(
                                    context.getString(com.example.myapplication.R.string.SAVED_SEARCH),
                                    text
                                )
                                apply()
                            }
                        }

                    }

                },
                1,
                OnProgressEnabled,
                OnBottomProgressEnabled,
                OnError
            )

        }
    }

    override fun fetchSongsApi(
        id: Int?,
        onSongViewModelsDataAdd: OnSongViewModelsDataAddListener,
        onProgress: OnProgressEnabledListener,
        onBottomProgress: OnBottomProgressEnabledListener,
        onError: OnErrorListener
    ) {
        if (page != null) {
            requestCall(
                PublicApi.publicApi.service.searchSongs(id, page),
                object : DefaultCallback<SearchModelSong>() {
                    override fun onResponse(
                        call: Call<SearchModelSong>,
                        response: Response<SearchModelSong>
                    ) {
                        val songViewModels = ArrayList<SongViewModel>()
                        for (song in response.body()?.response?.songs!!) {
                            songViewModels.add(SongViewModel.songModelConverter(song))
                        }

                        page = response.body()?.response?.nextPage
                        onSongViewModelsDataAdd.invoke(songViewModels)
                    }
                },
                page,
                onProgress,
                onBottomProgress,
                onError
            )
        }
    }

    override fun fetchSongsDb(
        OnSongViewModelsDbDataAdd: OnSongViewModelsDbDataAddListener,
        OnFavouritesEmpty: OnFavouritesEmptyListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener
    ) {
        val songViewModels = ArrayList<SongViewModel>()

        GlobalScope.launch {

            val songs = favouritesDao?.getFavourites()!!

            if (!songs.isNullOrEmpty()) {

                for (song in songs) {
                    songViewModels.add(SongViewModel().apply {
                        this.name = song.name
                        this.songArtImageUrl = song.songArtImageUrl
                        this.title = song.title
                    })

                    OnSongViewModelsDbDataAdd.invoke(songViewModels)
                }

                OnFavouritesEmpty.invoke(false)
            } else {
                OnFavouritesEmpty.invoke(true)
            }
        }
    }

    override fun getNextPageResults(
        context: Context,
        OnHitViewModelsDataAdd: OnHitViewModelsDataAddListener,
        OnProgressEnabled: OnProgressEnabledListener,
        OnBottomProgressEnabled: OnBottomProgressEnabledListener,
        OnError: OnErrorListener
    ) {
        lastSearch = sharedPrefs.getString(
            context.getString(R.string.SAVED_SEARCH),
            context.getString(R.string.DEFAULT_SEARCH_VALUE)
        ) ?: ""

        mPage = mPage?.plus(1)

        requestCall(
            PublicApi.publicApi.service.search(lastSearch, page),
            object : DefaultCallback<SearchModelHit>() {
                override fun onResponse(
                    call: Call<SearchModelHit>,
                    response: Response<SearchModelHit>
                ) {

                    createModels(response) {
                        OnHitViewModelsDataAdd.invoke(it)
                    }

                }

            },
            mPage?.plus(1),
            OnProgressEnabled,
            OnBottomProgressEnabled,
            OnError
        )
    }

    private fun createModels(
        response: Response<SearchModelHit>,
        onFinished: (hitViewModels: ArrayList<HitViewModel>) -> Unit = {}
    ) {
        if (response.body()?.response?.hits.isNullOrEmpty()) {
            mPage = null
        } else {
            val hitViewModels = ArrayList<HitViewModel>()
            for (hit in response.body()?.response?.hits!!) {
                hitViewModels.add(HitViewModel.hitModelConverter(hit))
            }

            onFinished(hitViewModels)
        }
    }

}