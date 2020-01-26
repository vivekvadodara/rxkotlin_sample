package com.rxjava2.android.samples.ui.search

import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by amitshekhar on 15/10/17.
 */
class SearchActivity : AppCompatActivity() {
    private var searchView: SearchView? = null
    private var textViewResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //searchView = findViewById<SearchView?>(R.id.searchView)
//        textViewResult = findViewById<TextView?>(R.id.textViewResult)
        setUpSearchObservable()
    }

    private fun setUpSearchObservable() {
        RxSearchObservable.fromView(searchView)!!
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(object : Predicate<String?> {
                    override fun test(text: String): Boolean {
                        return if (text.isEmpty()) {
                            textViewResult!!.setText("")
                            false
                        } else {
                            true
                        }
                    }
                })
                .distinctUntilChanged()!!
                .switchMap(object : Function<String?, ObservableSource<String?>> {
                    override fun apply(query: String): ObservableSource<String?> {
                        return dataFromNetwork(query)!!
                                .doOnError(Consumer { throwable: Throwable -> }) // continue emission in case of error also
                                .onErrorReturn { throwable: Throwable -> "" }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result -> textViewResult!!.setText(result) }
    }

    /**
     * Simulation of network data
     */
    private fun dataFromNetwork(query: String?): Observable<String?>? {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map { query }
    }

    companion object {
        val TAG = SearchActivity::class.java.simpleName
    }
}