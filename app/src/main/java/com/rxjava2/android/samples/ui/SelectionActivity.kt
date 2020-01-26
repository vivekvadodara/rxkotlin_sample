package com.rxjava2.android.samples.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.MyApplication
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.ui.cache.CacheExampleActivity
import com.rxjava2.android.samples.ui.compose.ComposeOperatorExampleActivity
import com.rxjava2.android.samples.ui.networking.NetworkingActivity
//import com.rxjava2.android.samples.ui.pagination.PaginationActivity
import com.rxjava2.android.samples.ui.rxbus.RxBusActivity
import com.rxjava2.android.samples.ui.search.SearchActivity

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
    }

    fun startOperatorsActivity(view: View?) {
        startActivity(Intent(this@SelectionActivity, OperatorsActivity::class.java))
    }

    fun startNetworkingActivity(view: View?) {
        startActivity(Intent(this@SelectionActivity, NetworkingActivity::class.java))
    }

    fun startCacheActivity(view: View?) {
        startActivity(Intent(this@SelectionActivity, CacheExampleActivity::class.java))
    }

    fun startRxBusActivity(view: View?) {
        (application as MyApplication).sendAutoEvent()
        startActivity(Intent(this@SelectionActivity, RxBusActivity::class.java))
    }

//    fun startPaginationActivity(view: View?) {
//        startActivity(Intent(this@SelectionActivity, PaginationActivity::class.java))
//    }

    fun startComposeOperator(view: View?) {
        startActivity(Intent(this@SelectionActivity, ComposeOperatorExampleActivity::class.java))
    }

    fun startSearchActivity(view: View?) {
        startActivity(Intent(this@SelectionActivity, SearchActivity::class.java))
    }
}