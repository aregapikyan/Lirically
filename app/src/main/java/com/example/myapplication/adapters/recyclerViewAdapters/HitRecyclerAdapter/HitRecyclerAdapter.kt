package com.example.myapplication.adapters.recyclerViewAdapters.HitRecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.addidions.helpers.load
import com.example.myapplication.mvp.models.hitModel.HitViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_lyrics.view.*
import kotlinx.android.synthetic.main.view_item.view.*
import kotlinx.android.synthetic.main.view_item.view.songArtIV

class HitRecyclerAdapter(
    var context: Context,
    val itemViewClickListener: OnItemClickListener,
    val songArtClickListener: OnItemClickListener
) : RecyclerView.Adapter<HitRecyclerAdapter.ViewHolder>() {

    var hitViewModels: ArrayList<HitViewModel> = arrayListOf()

    override fun getItemCount(): Int {
        return hitViewModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hitViewModels[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
        return ViewHolder(
            itemView,
            itemViewClickListener,
            songArtClickListener
        )
    }

    class ViewHolder(
        itemView: View,
        itemViewClickListener: OnItemClickListener,
        songArtClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val artistImageView: CircleImageView = itemView.findViewById(R.id.artistIV)
        private val songArtImageView: ImageView = itemView.findViewById(R.id.songArtIV)
        private val artistNameTextView: TextView = itemView.findViewById(R.id.songArtistTV)
        private val songTitleTextView: TextView = itemView.findViewById(R.id.songTitleTV)

        lateinit var hitViewModel: HitViewModel

        init {
            itemView.artistIV.setOnClickListener {
                songArtClickListener.onClick(hitViewModel, itemView)
            }

            itemView.songArtIV.setOnClickListener {
                itemViewClickListener.onClick(hitViewModel, itemView)
            }
        }

        fun bind(
            hitViewModel: HitViewModel
        ) {

            this.hitViewModel = hitViewModel

            artistNameTextView.text = hitViewModel.name
            songTitleTextView.text = hitViewModel.title

            artistImageView.load(hitViewModel.imageUrl)

            songArtImageView.load(hitViewModel.songArtImageThumbnailUrl)

        }

    }

    fun replaceHitViewModels(data: ArrayList<HitViewModel>) {

        hitViewModels = data
        notifyDataSetChanged()

    }

    fun addHitViewModels(data: ArrayList<HitViewModel>) {
        val lastIndex = hitViewModels.size
        hitViewModels.addAll(data)
        notifyItemRangeInserted(lastIndex, data.size)

    }

}