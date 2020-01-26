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
import io.reactivex.functions.Predicate
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by amitshekhar on 27/08/16.
 */
class FilterExampleActivity : AppCompatActivity() {
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
     * simple example by using filter operator to emit only even value
     *
     */
    private fun doSomeWork() {
        Observable.just<Int>(1, 2, 3, 4, 5, 6)
                .filter(object : Predicate<Int> {
                    override fun test(integer: Int): Boolean {
                        return ((integer!! % 2) == 0)
                    }
                })
                .subscribe(getObserver()!!)
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
        private val TAG = FilterExampleActivity::class.java.simpleName
    }
}