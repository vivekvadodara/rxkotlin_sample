package com.rxjava2.android.samples.ui.cache.source

import com.rxjava2.android.samples.ui.cache.model.Data
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Class to simulate Disk DataSource
 */
class DiskDataSource {
    private var data: Data? = null
    fun getData(): Observable<Data?>? {
        return Observable.create { emitter: ObservableEmitter<Data?>? ->
            if (data != null) {
                emitter!!.onNext(data!!)
            }
            emitter!!.onComplete()
        }
    }

    fun saveToDisk(data: Data?) {
//        this.data = data.clone()
        this.data!!.source = "disk"
    }
}