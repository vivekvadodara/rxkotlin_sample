package com.rxjava2.android.samples.ui.rxbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by amitshekhar on 06/02/17.
 */
class RxBus {
    private val bus: PublishSubject<Any?>? = PublishSubject.create()
    fun send(o: Any?) {
        bus!!.onNext(o!!)
    }

    fun toObservable(): Observable<Any?>? {
        return bus
    }

    fun hasObservers(): Boolean {
        return bus!!.hasObservers()
    }
}