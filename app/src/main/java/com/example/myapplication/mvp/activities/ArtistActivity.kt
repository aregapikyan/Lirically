package com.example.myapplication.mvp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter.SongRecyclerAdapter
import com.example.myapplication.addidions.helpers.load
import com.example.myapplication.managers.provider.Provider
import com.example.myapplication.mvp.base.AppActivity
import com.example.myapplication.mvp.models.songModel.SongViewModel
import com.example.myapplication.mvp.presenters.ArtistPresenter
import com.example.myapplication.mvp.views.ArtistView
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.view_item_song.view.*

class ArtistActivity : AppActivity<ArtistView, ArtistPresenter>(),
    ArtistView {

    var layoutManager = LinearLayoutManager(this)

    companion object {
        const val IMAGE_URL_KEY = "IMAGE_URL"
        const val ARTIST_NAME_KEY = "ARTIST_NAME"
        const val ARTIST_ID_KEY = "ARTIST_ID"

        fun start(
            context: Context,
            options: Bundle,
            url: String?,
            artistName: String?,
            artistId: Int?
        ) {
            val intent = Intent(context, ArtistActivity::class.java)
            intent.putExtra(IMAGE_URL_KEY, url)
            intent.putExtra(ARTIST_NAME_KEY, artistName)
            intent.putExtra(ARTIST_ID_KEY, artistId)
            context.startActivity(intent, options)
        }
    }

    private val imageUrl by lazy {
        intent.getStringExtra(IMAGE_URL_KEY) ?: ""
    }

    private val artistName by lazy {
        intent.getStringExtra(ARTIST_NAME_KEY) ?: ""
    }

    private val artistId by lazy {
        intent.getIntExtra(ARTIST_ID_KEY, 488)
    }

    private var adapter =
        SongRecyclerAdapter(
            this,
            object :
                com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter.OnItemClickListener {
                override fun onItemClick(viewModel: SongViewModel, itemView: View) {

                    LyricsApiActivity.start(
                        this@ArtistActivity,
                        viewModel.name,
                        viewModel.title,
                        viewModel.songArtImageUrl,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@ArtistActivity,
                            itemView.songArtIV,
                            ViewCompat.getTransitionName(itemView.songArtIV)!!
                        ).toBundle()!!
                    )
                }
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        getPresenter().fetchSongs(artistId)

        supportPostponeEnterTransition()

        artistImgHeaderIV.load(imageUrl) {
            supportStartPostponedEnterTransition()
        }

        initRecyclerView()
        initToolbar()
        initRecyclerViewScrollListener()

    }

    override fun onSongViewModelsDataAdd(data: ArrayList<SongViewModel>) {
        adapter.addSongViewModels(data)
    }

    override fun createPresenter(): ArtistPresenter = ArtistPresenter(
        this, artistId, Provider.getHitSongManager(this)
    )

    override fun onBackPressed() {
        finish()
    }

    private fun initRecyclerViewScrollListener() {
        searchSongRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                getPresenter().onRecyclerViewScrolled(
                    layoutManager.itemCount,
                    layoutManager.findLastCompletelyVisibleItemPosition()
                )

            }
        })

    }

    private fun initRecyclerView() {
        searchSongRV.adapter = adapter
        searchSongRV.layoutManager = layoutManager
    }

    private fun initToolbar() {
        artistToolbar.title = artistName

        artistToolbar.navigationIcon = getDrawable(R.drawable.ic_navigate_before_white)
        setSupportActionBar(artistToolbar)

        artistToolbar.setNavigationOnClickListener {
            finish()
        }
    }


}