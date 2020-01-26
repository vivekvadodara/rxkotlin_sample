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
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit

/**
 * Created by threshold on 2017/1/11.
 */
class ThrottleFirstExampleActivity : AppCompatActivity() {
    //var btn: Button? = null
    //var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn!!.setOnClickListener { doSomeWork() }
    }

    /*
    * Using throttleFirst() -> if the source Observable has emitted no items since
    * the last time it was sampled, the Observable that results from this operator
    * will emit no item for that sampling period.
    */
    private fun doSomeWork() {
        getObservable()
                .throttleFirst(500, TimeUnit.MILLISECONDS) // Run on a background thread
                .subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<Int> {
        return Observable.create(object : ObservableOnSubscribe<Int> {
            @Throws(Exception::class)
            override fun subscribe(emitter: ObservableEmitter<Int>) { // send events with simulated time wait
                Thread.sleep(0)
                emitter!!.onNext(1) // deliver
                emitter!!.onNext(2) // skip
                Thread.sleep(505)
                emitter!!.onNext(3) // deliver
                Thread.sleep(99)
                emitter!!.onNext(4) // skip
                Thread.sleep(100)
                emitter!!.onNext(5) // skip
                emitter!!.onNext(6) // skip
                Thread.sleep(305)
                emitter!!.onNext(7) // deliver
                Thread.sleep(510)
                emitter!!.onComplete()
            }
        })
    }

    private fun getObserver(): Observer<Int?>? {
        return object : Observer<Int?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onNext(value: Int) {
                textView!!.append(" onNext : ")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                textView!!.append(" value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onNext ")
                Log.d(TAG, " value : $value")
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
        private val TAG = ThrottleFirstExampleActivity::class.java.simpleName
    }
}