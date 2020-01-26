package com.rxjava2.android.samples.ui.cache.source

import com.rxjava2.android.samples.ui.cache.model.Data
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Class to simulate Network DataSource
 */
class NetworkDataSource {
    fun getData(): Observable<Data?>? {
        return Observable.create { emitter: ObservableEmitter<Data?>? ->
            val data = Data()
            data.source = "network"
            emitter!!.onNext(data)
            emitter!!.onComplete()
        }
    }
}