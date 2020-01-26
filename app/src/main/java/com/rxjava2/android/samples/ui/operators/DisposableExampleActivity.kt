package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.os.SystemClock
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
import kotlinx.android.synthetic.main.activity_example.*

import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by amitshekhar on 27/08/16.
 */
class DisposableExampleActivity : AppCompatActivity() {
    //var btn: Button? = null
    //var textView: TextView? = null
    private val disposables: CompositeDisposable? = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn.setOnClickListener { doSomeWork() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables!!.clear() // do not send event after activity has been destroyed
    }

    /*
     * Example to understand how to use disposables!!.
     * disposables is cleared in onDestroy of this activity.
     */
    fun doSomeWork() {
        disposables!!.add(sampleObservable() // Run on a background thread
                !!.subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String?>() {
                    override fun onComplete() {
                        textView!!.append(" onComplete")
                        textView!!.append(AppConstant.LINE_SEPARATOR)
                        Log.d(TAG, " onComplete")
                    }

                    override fun onError(e: Throwable) {
                        textView!!.append(" onError : " + e.message)
                        textView!!.append(AppConstant.LINE_SEPARATOR)
                        Log.d(TAG, " onError : " + e.message)
                    }

                    override fun onNext(value: String) {
                        textView!!.append(" onNext : value : $value")
                        textView!!.append(AppConstant.LINE_SEPARATOR)
                        Log.d(TAG, " onNext value : $value")
                    }
                }))
    }

    companion object {
        private val TAG = DisposableExampleActivity::class.java.simpleName
        fun sampleObservable(): Observable<String?>? {
            return Observable.defer {
                // Do some long running operation
                SystemClock.sleep(2000)
                Observable.just("one", "two", "three", "four", "five")
            }
        }
    }
}