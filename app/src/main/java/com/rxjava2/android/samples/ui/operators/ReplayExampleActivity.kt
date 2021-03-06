package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.utils.AppConstant
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by amitshekhar on 27/08/16.
 */
class ReplayExampleActivity : AppCompatActivity() {
    //var btn: Button? = null
    //var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn!!.setOnClickListener { doSomeWork() }
    }

    /* Using replay operator, replay ensure that all observers see the same sequence
     * of emitted items, even if they subscribe after the Observable has begun emitting items
     */
    private fun doSomeWork() {
        val source = PublishSubject.create<Int?>()
        val connectableObservable = source.replay(3) // bufferSize = 3 to retain 3 values to replay
        connectableObservable.connect() // connecting the connectableObservable
        connectableObservable.subscribe(getFirstObserver()!!)
        source.onNext(1)
        source.onNext(2)
        source.onNext(3)
        source.onNext(4)
        source.onComplete()
        /*
         * it will emit 2, 3, 4 as (count = 3), retains the 3 values for replay
         */connectableObservable.subscribe(getSecondObserver()!!)
    }

    private fun getFirstObserver(): Observer<Int?>? {
        return object : Observer<Int?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed())
            }

            override fun onNext(value: Int) {
                textView!!.append(" First onNext : value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " First onNext value : $value")
            }

            override fun onError(e: Throwable) {
                textView!!.append(" First onError : " + e.message)
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " First onError : " + e.message)
            }

            override fun onComplete() {
                textView!!.append(" First onComplete")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " First onComplete")
            }
        }
    }

    private fun getSecondObserver(): Observer<Int?>? {
        return object : Observer<Int?> {
            override fun onSubscribe(d: Disposable) {
                textView!!.append(" Second onSubscribe : isDisposed :" + d.isDisposed())
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed())
                textView!!.append(AppConstant.LINE_SEPARATOR)
            }

            override fun onNext(value: Int) {
                textView!!.append(" Second onNext : value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " Second onNext value : $value")
            }

            override fun onError(e: Throwable) {
                textView!!.append(" Second onError : " + e.message)
                Log.d(TAG, " Second onError : " + e.message)
            }

            override fun onComplete() {
                textView!!.append(" Second onComplete")
                Log.d(TAG, " Second onComplete")
            }
        }
    }

    companion object {
        private val TAG = ReplayExampleActivity::class.java.simpleName
    }
}