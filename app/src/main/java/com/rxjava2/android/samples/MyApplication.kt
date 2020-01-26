package com.rxjava2.android.samples

import android.app.Application
import com.rxjava2.android.samples.model.Events.AutoEvent
import com.rxjava2.android.samples.ui.rxbus.RxBus
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by threshold on 2017/1/12.
 */
class MyApplication : Application() {
    private var bus: RxBus? = null
    override fun onCreate() {
        super.onCreate()
        bus = RxBus()
    }

    fun bus(): RxBus? {
        return bus
    }

    fun sendAutoEvent() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe { bus!!.send(AutoEvent()) }
    }

    companion object {
        val TAG: String? = "MyApplication"
    }
}