package com.example.myapplication.mvp.models.songModel.searchSong

import com.example.myapplication.mvp.models.hitModel.searchHit.PrimaryArtistHit
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Song {

    @SerializedName("annotation_count")
    @Expose
    var annotationCount: Int? = null
    @SerializedName("api_path")
    @Expose
    var apiPath: String? = null
    @SerializedName("full_title")
    @Expose
    var fullTitle: String? = null
    @SerializedName("header_image_thumbnail_url")
    @Expose
    var headerImageThumbnailUrl: String? = null
    @SerializedName("header_image_url")
    @Expose
    var headerImageUrl: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("lyrics_owner_id")
    @Expose
    var lyricsOwnerId: Int? = null
    @SerializedName("lyrics_state")
    @Expose
    var lyricsState: String? = null
    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("pyongs_count")
    @Expose
    var pyongsCount: Any? = null
    @SerializedName("song_art_image_thumbnail_url")
    @Expose
    var songArtImageThumbnailUrl: String? = null
    @SerializedName("song_art_image_url")
    @Expose
    var songArtImageUrl: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("title_with_featured")
    @Expose
    var titleWithFeatured: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("primary_artist")
    @Expose
    var primaryArtist: PrimaryArtistHit? = null

}