package com.example.myapplication.adapters.recyclerViewAdapters.HitRecyclerAdapter

import android.view.View
import com.example.myapplication.mvp.models.hitModel.HitViewModel

interface OnItemClickListener {
    fun onClick(viewModel: HitViewModel, itemView: View)
}
