package com.example.myapplication.mvp.models.hitModel.searchHit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponseModelHit {

    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null

}