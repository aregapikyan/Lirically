package com.example.myapplication.mvp.models.hitModel

import com.example.myapplication.mvp.models.hitModel.searchHit.Hit

class HitViewModel {

    companion object {
        fun hitModelConverter(hit: Hit): HitViewModel {
            val hitViewModel = HitViewModel()
            hitViewModel.songArtImageUrl = hit.result?.songArtImageUrl
            hitViewModel.imageUrl = hit.result?.primaryArtist?.imageUrl
            hitViewModel.title = hit.result?.title
            hitViewModel.name = hit.result?.primaryArtist?.name
            hitViewModel.id = hit.result?.primaryArtist?.id
            hitViewModel.songArtImageThumbnailUrl = hit.result?.songArtImageThumbnailUrl
            return hitViewModel
        }
    }

    var songArtImageUrl: String? = null
    var imageUrl: String? = null
    var songArtImageThumbnailUrl: String? = null
    var title: String? = null
    var name: String? = null
    var id: Int? = null
}