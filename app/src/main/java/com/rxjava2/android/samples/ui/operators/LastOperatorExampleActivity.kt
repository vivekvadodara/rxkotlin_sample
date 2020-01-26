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
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by techteam on 13/09/16.
 */
class LastOperatorExampleActivity : AppCompatActivity() {
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
    * last() emits only the last item emitted by the Observable.
    */
    private fun doSomeWork() {
        // the default item ("A1") to emit if the source ObservableSource is empty
        getObservable().last("A1")!!.subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6")
    }

    private fun getObserver(): SingleObserver<String> {
        return object : SingleObserver<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onSuccess(value: String) {
                textView!!.append(" onNext : value : $value")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onNext value : $value")
            }

            override fun onError(e: Throwable) {
                textView!!.append(" onError : " + e.message)
                textView!!.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e.message)
            }
        }
    }

    companion object {
        private val TAG = DistinctExampleActivity::class.java.simpleName
    }
}