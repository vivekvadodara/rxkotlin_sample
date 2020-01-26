package com.rxjava2.android.samples.ui.operators

import android.util.Log
import com.rxjava2.android.samples.utils.AppConstant
import com.rxjava2.android.samples.utils.ObserverAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit

class TakeUntilExampleActivity : TakeOperatorBaseActivity() {
    override fun doSomeWork() {
        val timerObservable = Observable.timer(5, TimeUnit.SECONDS)
        timerObservable.subscribe(object : ObserverAdapter<Long?>() {
            override fun onComplete() {
                val print = " Timer completed"
                textView!!.append(print)
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, print)
            }
        })
        getStringObservable() //Delay item emission by one second
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), object : BiFunction<String, Long, String> {
                    @Throws(Exception::class)
                    override fun apply(s: String, aLong: Long): String {
                        return s
                    }
                }) //Will receive the items from Strings observable until timerObservable doesn't start emitting data.
                .takeUntil(timerObservable) //We need to observe on MainThread because delay works on background thread to avoid UI blocking.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    companion object {
        private val TAG = TakeWhileExampleActivity::class.java.simpleName
    }
}