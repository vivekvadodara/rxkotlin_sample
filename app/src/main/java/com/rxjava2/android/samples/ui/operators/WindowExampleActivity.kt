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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit

class WindowExampleActivity : AppCompatActivity() {
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
     * Example using window operator -> It periodically
     * subdivide items from an Observable into
     * Observable windows and emit these windows rather than
     * emitting the items one at a time
     */
    protected fun doSomeWork() {
//        Observable.interval(1, TimeUnit.SECONDS).take(12)
//                .window(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getConsumer())
    }

//    fun getConsumer(): Consumer<Observable<Long?>?>? {
//        return Consumer { observable ->
//            Log.d(TAG, "Sub Divide begin....")
//            textView!!.append("Sub Divide begin ....")
//            textView!!.append(AppConstant.LINE_SEPARATOR)
//            getObservable
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe { value ->
//                        Log.d(TAG, "Next:$value")
//                        textView!!.append("Next:$value")
//                        textView!!.append(AppConstant.LINE_SEPARATOR)
//                    }
//        }
//    }

    companion object {
        private val TAG = WindowExampleActivity::class.java.simpleName
    }
}