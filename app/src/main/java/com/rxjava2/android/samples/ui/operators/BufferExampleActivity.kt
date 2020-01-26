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
 * Created by amitshekhar on 27/08/16.
 */
class BufferExampleActivity : AppCompatActivity() {
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
     * simple example using buffer operator - bundles all emitted values into a list
     */
    private fun doSomeWork() {
        val buffered = getObservable()!!.buffer(3, 1)
        // 3 means,  it takes max of three from its start index and create list
// 1 means, it jumps one step every time
// so the it gives the following list
// 1 - one, two, three
// 2 - two, three, four
// 3 - three, four, five
// 4 - four, five
// 5 - five
        buffered.subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<String?>? {
        return Observable.just("one", "two", "three", "four", "five")
    }

    private fun getObserver(): Observer<MutableList<String?>?> {
        return object : Observer<MutableList<String?>?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onNext(stringList: MutableList<String?>) {
                textView!!.append(" onNext size : " + stringList.size)
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onNext : size :" + stringList.size)
                for (value in stringList) {
                    textView!!.append(" value : $value")
                    textView!!.append(AppConstant.LINE_SEPARATOR)
                    Log.d(TAG, " : value :$value")
                }
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
        private val TAG = BufferExampleActivity::class.java.simpleName
    }
}