package com.example.myapplication.adapters.recyclerViewAdapters.SongRecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.addidions.helpers.load
import com.example.myapplication.mvp.models.songModel.SongViewModel

class SongRecyclerAdapter(
    var context: Context,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SongRecyclerAdapter.Companion.ViewHolder>() {

    var songViewModels: ArrayList<SongViewModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.view_item_song,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return songViewModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, songViewModels[position], itemClickListener)

    }

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val songArtImageView = itemView.findViewById<ImageView>(R.id.songArtIV)
            val songTitleTextView = itemView.findViewById<TextView>(R.id.songTitleTV)

            var songViewModel = SongViewModel()

            val viewForeground = itemView.findViewById<View>(R.id.viewForeground)
            val viewBackground = itemView.findViewById<View>(R.id.viewBackground)

            fun bind(
                context: Context,
                songViewModel: SongViewModel,
                clickListener: OnItemClickListener,
                onLoadingFinished: () -> Unit = {}
            ) {

                this.songViewModel = songViewModel

                songTitleTextView.text = songViewModel.title

                songArtImageView.load(songViewModel.songArtImageUrl) {
                    onLoadingFinished()
                }

                itemView.setOnClickListener {
                    clickListener.onItemClick(songViewModel, itemView)
                }
            }

        }
    }

    fun addSongViewModels(data: ArrayList<SongViewModel>) {
        val lastIndex = songViewModels.size
        songViewModels.addAll(data)
        notifyItemRangeInserted(lastIndex, data.size)
    }

    fun replaceSongViewModels(data: ArrayList<SongViewModel>) {
        songViewModels = data
        notifyDataSetChanged()
    }

}