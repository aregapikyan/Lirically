package com.example.myapplication.mvp.models.hitModel.searchHit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchModelHit {
    @SerializedName("response")
    @Expose
    var response: SearchResponseModelHit? = null
}