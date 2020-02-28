package com.example.myapplication.listeners

import android.app.Activity
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.myapplication.adapters.recyclerViewAdapters.HitRecyclerAdapter.OnItemClickListener
import com.example.myapplication.mvp.activities.LyricsApiActivity
import com.example.myapplication.mvp.activities.LyricsBaseActivity
import com.example.myapplication.mvp.models.hitModel.HitViewModel
import kotlinx.android.synthetic.main.view_item.view.*

class OnSongArtClickListener(var activity: Activity) : OnItemClickListener {
    override fun onClick(viewModel: HitViewModel, itemView: View) {
        LyricsBaseActivity.start(
            activity,
            viewModel.name,
            viewModel.title,
            viewModel.songArtImageUrl,
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                itemView.songArtIV,
                ViewCompat.getTransitionName(itemView.songArtIV)!!
            ).toBundle()!!,
            LyricsApiActivity()
        )
    }
}