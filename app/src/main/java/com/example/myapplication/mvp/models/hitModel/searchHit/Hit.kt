package com.example.myapplication.mvp.models.hitModel.searchHit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hit {

    @SerializedName("highlights")
    @Expose
    var highlights: List<Any>? = null
    @SerializedName("index")
    @Expose
    var index: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("result")
    @Expose
    var result: Result? = null

}