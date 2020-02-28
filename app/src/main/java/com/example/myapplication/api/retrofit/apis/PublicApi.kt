package com.example.myapplication.api.retrofit.apis

import com.example.myapplication.api.retrofit.BASE_URL
import com.example.myapplication.api.retrofit.servicies.PublicApiService

class PublicApi : BaseApi<PublicApiService>(
    BASE_URL,
    PublicApiService::class.java
) {
    companion object {
        val publicApi: PublicApi by lazy {
            PublicApi()
        }
    }
}

