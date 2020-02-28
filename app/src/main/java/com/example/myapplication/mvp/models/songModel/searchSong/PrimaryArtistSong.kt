package com.example.myapplication.mvp.models.songModel.searchSong


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrimaryArtistSong {

    @SerializedName("api_path")
    @Expose
    var apiPath: String? = null
    @SerializedName("header_image_url")
    @Expose
    var headerImageUrl: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null
    @SerializedName("is_meme_verified")
    @Expose
    var isMemeVerified: Boolean? = null
    @SerializedName("is_verified")
    @Expose
    var isVerified: Boolean? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("iq")
    @Expose
    var iq: Int? = null

}