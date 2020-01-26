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
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by techteam on 13/09/16.
 */
class DistinctExampleActivity : AppCompatActivity() {
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
     * distinct() suppresses duplicate items emitted by the source Observable.
     */
    private fun doSomeWork() {
        getObservable()!!.distinct().subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<Int?>? {
        return Observable.just(1, 2, 1, 1, 2, 3, 4, 6, 4)
    }

    private fun getObserver(): Observer<Int?>? {
        return object : Observer<Int?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onNext(value: Int) {
                textView!!.append(" onNext : value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onNext value : $value")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, " onComplete")
            }
        }
    }

    companion object {
        private val TAG = DistinctExampleActivity::class.java.simpleName
    }
}