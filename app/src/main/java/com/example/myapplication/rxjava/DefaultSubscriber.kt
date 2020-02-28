package com.example.myapplication.rxjava

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class DefaultSubscriber<T> : Subscriber<T> {

    override fun onComplete() {

    }
    override fun onError(t: Throwable?) {

    }

}