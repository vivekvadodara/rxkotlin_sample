package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.utils.AppConstant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit

/**
 * Created by amitshekhar on 27/08/16.
 */
class IntervalExampleActivity : AppCompatActivity() {
    //var btn: Button? = null
    //var textView: TextView? = null
    private val disposables: CompositeDisposable? = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn!!.setOnClickListener { doSomeWork() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables!!.clear() // clearing it : do not emit after destroy
    }

    /*
     * simple example using interval to run task at an interval of 2 sec
     * which start immediately
     */
    private fun doSomeWork() {
        disposables!!.add(getObservable() // Run on a background thread
                !!.subscribeOn(Schedulers.io()) // Be notified on the main thread
                !!.observeOn(AndroidSchedulers.mainThread())
                !!.subscribeWith(getObserver()!!))
    }

    private fun getObservable(): Observable<out Long?>? {
        return Observable.interval(0, 2, TimeUnit.SECONDS)
    }

    private fun getObserver(): DisposableObserver<Long> {
        return object : DisposableObserver<Long>() {
            override fun onNext(value: Long) {
                textView!!.append(" onNext : value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onNext : value : $value")
            }

            override fun onError(e: Throwable) {
                textView!!.append(" onError : " + e.message)
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView!!.append(" onComplete")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }
        }
    }

    companion object {
        private val TAG = IntervalExampleActivity::class.java.simpleName
    }
}