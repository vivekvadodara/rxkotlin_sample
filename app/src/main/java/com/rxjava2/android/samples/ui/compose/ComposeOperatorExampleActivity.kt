package com.rxjava2.android.samples.ui.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import io.reactivex.Flowable
import io.reactivex.Observable

class ComposeOperatorExampleActivity : AppCompatActivity() {
    private val schedulers: RxSchedulers? = RxSchedulers()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_operator_example)
        /*
            Compose for reusable code.
         */Observable.just<Int?>(1, 2, 3, 4, 5)
                .compose(schedulers!!.applyObservableAsync())
                .subscribe()
        Flowable.just<Int?>(1, 2, 3, 4, 5)
                .compose(schedulers.applyFlowableAsysnc())
                .subscribe()
    }
}