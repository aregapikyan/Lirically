package com.example.myapplication.api.retrofit.apis

import com.example.myapplication.api.retrofit.LYRICS_BASE_URL
import com.example.myapplication.api.retrofit.servicies.LyricsPublicApiService

class LyricsPublicApi : BaseApi<LyricsPublicApiService>(
    LYRICS_BASE_URL,
    LyricsPublicApiService::class.java
) {
    companion object {
        val publicApi: LyricsPublicApi by lazy {
            LyricsPublicApi()
        }
    }
}