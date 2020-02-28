package com.example.myapplication.listeners

import android.app.Activity
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.example.myapplication.adapters.recyclerViewAdapters.HitRecyclerAdapter.OnItemClickListener
import com.example.myapplication.mvp.activities.ArtistActivity
import com.example.myapplication.mvp.models.hitModel.HitViewModel
import kotlinx.android.synthetic.main.view_item.view.*


class OnArtistImageClickListener(var activity: Activity) : OnItemClickListener {
    override fun onClick(viewModel: HitViewModel, itemView: View) {
        val artistImgPair: Pair<View, String> =
            Pair(
                itemView.artistIV,
                ViewCompat.getTransitionName(itemView.artistIV)!!
            )
        val songArtistTxtPair: Pair<View, String> =
            Pair(
                itemView.songArtistTV,
                ViewCompat.getTransitionName(itemView.songArtistTV)!!
            )
        ArtistActivity.start(
            activity,
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                artistImgPair,
                songArtistTxtPair
            ).toBundle()!!,
            viewModel.imageUrl,
            viewModel.name,
            viewModel.id
        )
    }
}