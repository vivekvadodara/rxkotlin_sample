package com.rxjava2.android.samples.utils

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Adapter class for Observer to get the method only needed by the user. Instead of overriding each and every method.
 *
 * @param <T>
</T> */
abstract class ObserverAdapter<T> : Observer<T> {
    override fun onSubscribe(d: Disposable) {}
    override fun onNext(s: T) {}
    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}