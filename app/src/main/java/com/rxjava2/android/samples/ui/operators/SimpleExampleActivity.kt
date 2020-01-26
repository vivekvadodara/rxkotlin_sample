package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.utils.AppConstant
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by amitshekhar on 27/08/16.
 */
class SimpleExampleActivity : AppCompatActivity() {
    //    //var btn: Button? = null
    //var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn.setOnClickListener { doSomeWork() }
    }

    /*
     * simple example to emit two value one by one
     */
    private fun doSomeWork() {
        getObservable() // Run on a background thread
                .subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("Cricket", "Football")
    }

    private fun getObserver(): Observer<String?>? {
        return object : Observer<String?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onNext(value: String) {
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
        private val TAG = SimpleExampleActivity::class.java.simpleName
    }
}