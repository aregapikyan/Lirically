package com.example.myapplication.mvp.models.hitModel.searchHit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stats {

    @SerializedName("unreviewed_annotations")
    @Expose
    var unreviewedAnnotations: Int? = null
    @SerializedName("hot")
    @Expose
    var hot: Boolean? = null
    @SerializedName("pageviews")
    @Expose
    var pageviews: Int? = null

}