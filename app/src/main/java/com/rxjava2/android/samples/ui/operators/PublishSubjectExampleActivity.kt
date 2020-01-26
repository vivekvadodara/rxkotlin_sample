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
 * Created by amitshekhar on 17/12/16.
 */
class PublishSubjectExampleActivity : AppCompatActivity() {
    //var btn: Button? = null
    //var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        //btn = findViewById<Button?>(R.id.btn)
        //textView = findViewById<TextView?>(R.id.textView)
        btn!!.setOnClickListener { doSomeWork() }
    }

    /* PublishSubject emits to an observer only those items that are emitted
     * by the source Observable, subsequent to the time of the subscription.
     */
    private fun doSomeWork() {
        val source = PublishSubject.create<Int?>()
        source.subscribe(getFirstObserver()!!) // it will get 1, 2, 3, 4 and onComplete
        source.onNext(1)
        source.onNext(2)
        source.onNext(3)
        /*
         * it will emit 4 and onComplete for second observer also.
         */source.subscribe(getSecondObserver()!!)
        source.onNext(4)
        source.onComplete()
    }

    private fun getFirstObserver(): Observer<Int?> {
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

    private fun getSecondObserver(): Observer<Int?> {
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
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " Second onError : " + e.message)
            }

            override fun onComplete() {
                textView!!.append(" Second onComplete")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " Second onComplete")
            }
        }
    }

    companion object {
        private val TAG = PublishSubjectExampleActivity::class.java.simpleName
    }
}