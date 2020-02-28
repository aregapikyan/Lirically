package com.example.myapplication.managers.base

import android.content.Context
import com.example.myapplication.OnBottomProgressEnabledListener
import com.example.myapplication.OnErrorListener
import com.example.myapplication.OnProgressEnabledListener
import com.example.myapplication.db.database.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseManagerImpl(context: Context) {

    val db = AppDatabase.getAppDataBase(context)
    val favouritesDao = db?.favouritesDao()

    open fun <T> requestCall(
        call: Call<T>,
        resultCallback: Callback<T>,
        page: Int?,
        onProgressEnabled: OnProgressEnabledListener,
        onBottomProgressEnabled: OnBottomProgressEnabledListener,
        onError: OnErrorListener
    ) {
        if (page == 1) {
            onProgressEnabled.invoke(true)
        } else {
            onBottomProgressEnabled.invoke(true)
        }
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (page == 1) {
                    onProgressEnabled.invoke(false)
                } else {
                    onBottomProgressEnabled.invoke(false)
                }

                resultCallback.onResponse(call, response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (page == 1) {
                    onProgressEnabled.invoke(false)
                } else {
                    onBottomProgressEnabled.invoke(false)
                }

                onError.invoke("No Internet Connection")

                resultCallback.onFailure(call, t)
            }

        })

    }
}