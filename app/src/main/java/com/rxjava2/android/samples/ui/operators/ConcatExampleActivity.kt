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
class ConcatExampleActivity : AppCompatActivity() {
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
     * Using concat operator to combine Observable : concat maintain
     * the order of Observable.
     * It will emit all the 7 values in order
     * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
     * first all from the first Observable and then
     * all from the second Observable all in order
     */
    private fun doSomeWork() {
        val aStrings = arrayOf<String?>("A1", "A2", "A3", "A4")
        val bStrings = arrayOf<String?>("B1", "B2", "B3")
        val aObservable = Observable.fromArray(*aStrings)
        val bObservable = Observable.fromArray(*bStrings)
        Observable.concat(aObservable, bObservable)
                .subscribe(getObserver()!!)
    }

    private fun getObserver(): Observer<String?> {
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
        private val TAG = ConcatExampleActivity::class.java.simpleName
    }
}