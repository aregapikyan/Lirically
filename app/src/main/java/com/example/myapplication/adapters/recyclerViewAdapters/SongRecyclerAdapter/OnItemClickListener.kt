package com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter

import android.view.View
import com.example.myapplication.mvp.models.songModel.SongViewModel

interface OnItemClickListener {
    fun onItemClick(viewModel: SongViewModel, itemView: View)
}