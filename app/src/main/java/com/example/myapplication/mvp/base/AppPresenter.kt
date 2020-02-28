package com.example.myapplication.mvp.base

import android.content.Context
import com.example.myapplication.OnBottomProgressEnabledListener
import com.example.myapplication.OnErrorListener
import com.example.myapplication.OnProgressEnabledListener

open class AppPresenter<V : AppView>(context: Context) {
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    val onProgressEnabled: OnProgressEnabledListener = {
        view?.onProgressEnabled(it)
    }

    val onBottomProgressEnabled: OnBottomProgressEnabledListener = {
        view?.onBottomProgressEnabled(it)
    }

    val onError: OnErrorListener = {
        view?.onError(it)
    }
}