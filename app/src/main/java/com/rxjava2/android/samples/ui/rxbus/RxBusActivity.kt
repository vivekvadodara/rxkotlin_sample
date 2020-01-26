package com.rxjava2.android.samples.ui.rxbus

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.MyApplication
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.Events.AutoEvent
import com.rxjava2.android.samples.model.Events.TapEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxbus.*

/**
 * Created by amitshekhar on 06/02/17.
 */
class RxBusActivity : AppCompatActivity() {
    //var textView: TextView? = null
    var button: Button? = null
    private val disposables: CompositeDisposable? = CompositeDisposable()
    override fun onDestroy() {
        super.onDestroy()
        disposables!!.clear() // do not send event after activity has been destroyed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbus)
        //textView = findViewById<TextView?>(R.id.textView)
//        button = findViewById<Button?>(R.id.button)
        disposables!!.add((application as MyApplication)
                .bus()!!
                .toObservable()!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { `object` ->
                    if (`object` is AutoEvent) {
                        textView!!.setText("Auto Event Received")
                    } else if (`object` is TapEvent) {
                        textView!!.setText("Tap Event Received")
                    }
                })
        button!!.setOnClickListener(View.OnClickListener {
            (application as MyApplication)
                    .bus()!!
                    .send(TapEvent())
        })
    }

    companion object {
        val TAG = RxBusActivity::class.java.simpleName
    }
}