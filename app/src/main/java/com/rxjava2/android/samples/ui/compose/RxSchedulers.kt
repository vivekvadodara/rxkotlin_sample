package com.rxjava2.android.samples.ui.compose

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

/**
 * Created by werockstar on 5/19/2017.
 */
class RxSchedulers {
    fun <T> applyObservableAsync(): ObservableTransformer<T?, T?> {
        return object : ObservableTransformer<T?, T?> {
            override fun apply(observable: Observable<T?>): ObservableSource<T?> {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun <T> applyObservableCompute(): ObservableTransformer<T?, T?> {
        return object : ObservableTransformer<T?, T?> {
            override fun apply(observable: Observable<T?>): ObservableSource<T?> {
                return observable.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun <T> applyObservableMainThread(): ObservableTransformer<T?, T?> {
        return object : ObservableTransformer<T?, T?> {
            override fun apply(observable: Observable<T?>): ObservableSource<T?> {
                return observable.observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun <T> applyFlowableMainThread(): FlowableTransformer<T?, T?> {
        return object : FlowableTransformer<T?, T?> {
            override fun apply(flowable: Flowable<T?>): Publisher<T?> {
                return flowable.observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun <T> applyFlowableAsysnc(): FlowableTransformer<T?, T?> {
        return object : FlowableTransformer<T?, T?> {
            override fun apply(flowable: Flowable<T?>): Publisher<T?> {
                return flowable.observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun <T> applyFlowableCompute(): FlowableTransformer<T?, T?> {
        return object : FlowableTransformer<T?, T?> {
            override fun apply(flowable: Flowable<T?>): Publisher<T?> {
                return flowable.observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}