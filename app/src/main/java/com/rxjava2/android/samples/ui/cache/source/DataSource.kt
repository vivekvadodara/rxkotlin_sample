package com.rxjava2.android.samples.ui.cache.source

import com.rxjava2.android.samples.ui.cache.model.Data
import io.reactivex.Observable

/**
 * The DataSource to handle 3 data sources - memory, disk, network
 */
class DataSource(private val memoryDataSource: MemoryDataSource?,
                 private val diskDataSource: DiskDataSource?,
                 private val networkDataSource: NetworkDataSource?) {
    fun getDataFromMemory(): Observable<Data?>? {
        return memoryDataSource!!.getData()
    }

    fun getDataFromDisk(): Observable<Data?>? {
        return diskDataSource!!.getData()!!.doOnNext { data: Data? -> memoryDataSource!!.cacheInMemory(data) }
    }

    fun getDataFromNetwork(): Observable<Data?>? {
        return networkDataSource!!.getData()!!.doOnNext { data: Data? ->
            diskDataSource!!.saveToDisk(data)
            memoryDataSource!!.cacheInMemory(data)
        }
    }

}