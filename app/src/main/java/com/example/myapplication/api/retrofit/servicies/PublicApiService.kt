package com.example.myapplication.api.retrofit.servicies

import com.example.myapplication.api.retrofit.X_RAPIDAPI_HOST
import com.example.myapplication.api.retrofit.X_RAPIDAPI_KEY
import com.example.myapplication.mvp.models.hitModel.searchHit.SearchModelHit
import com.example.myapplication.mvp.models.songModel.searchSong.SearchModelSong
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicApiService {
    @Headers(X_RAPIDAPI_HOST, X_RAPIDAPI_KEY)
    @GET("search")
    fun search(@Query("q") text: String, @Query("page") page: Int?): Call<SearchModelHit>

    @Headers(X_RAPIDAPI_HOST, X_RAPIDAPI_KEY)
    @GET("artists/{id}/songs")
    fun searchSongs(@Path("id") id: Int?, @Query("page") page: Int?): Call<SearchModelSong>

}