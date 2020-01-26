package com.rxjava2.android.samples.ui.operators

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit

class TakeWhileExampleActivity : TakeOperatorBaseActivity() {
    override fun doSomeWork() {
        getStringObservable() //Delay item emission by one second
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), object : BiFunction<String, Long, String> {
                    @Throws(Exception::class)
                    override fun apply(s: String, aLong: Long): String {
                        return s
                    }
                }) //Take the items until the condition is met.
                .takeWhile(object : Predicate<String> {
                    @Throws(Exception::class)
                    override fun test(s: String): Boolean {
                        return !s.toLowerCase().contains("honey")
                    }
                }) //We need to observe on MainThread because delay works on background thread to avoid UI blocking.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    companion object {
        private val TAG = TakeWhileExampleActivity::class.java.simpleName
    }
}