package com.rxjava2.android.samples.model

import io.reactivex.Observable

/**
 * Created by amitshekhar on 30/08/16.
 */
class Car {
    private var brand: String? = null
    fun setBrand(brand: String?) {
        this.brand = brand
    }

    fun brandDeferObservable(): Observable<String?>? {
        return Observable.defer { Observable.just(brand) }
    }
}