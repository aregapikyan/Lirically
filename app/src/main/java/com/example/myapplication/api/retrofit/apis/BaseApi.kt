package com.example.myapplication.api.retrofit.apis

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseApi<out S> constructor(baseUrl: String, apiInterfaceClass: Class<S>) {

    val service: S by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createDefaultHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create(createGsonBuilder()))
            .build()
            .create(apiInterfaceClass)
    }

    private fun createGsonBuilder() = GsonBuilder()
        .create()

    private fun createDefaultHttpClient(): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            .readTimeout(45, TimeUnit.SECONDS)
            .connectTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

}