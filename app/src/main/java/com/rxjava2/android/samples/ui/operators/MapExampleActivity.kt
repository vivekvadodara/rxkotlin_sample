package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.ApiUser
import com.rxjava2.android.samples.model.User
import com.rxjava2.android.samples.utils.AppConstant
import com.rxjava2.android.samples.utils.Utils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by amitshekhar on 27/08/16.
 */
class MapExampleActivity : AppCompatActivity() {
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
    * Here we are getting ApiUser Object from api server
    * then we are converting it into User Object because
    * may be our database support User Not ApiUser Object
    * Here we are using Map Operator to do that
    */
    private fun doSomeWork() {
        getObservable() // Run on a background thread
                .subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(object : Function<MutableList<ApiUser>, MutableList<User>> {
                    override fun apply(apiUsers: MutableList<ApiUser>): MutableList<User> {
                        return Utils.convertApiUserListToUserList(apiUsers)
                    }
                })
                .subscribe(getObserver()!!)
    }

    private fun getObservable(): Observable<MutableList<ApiUser>> {
        return Observable.create(object : ObservableOnSubscribe<MutableList<ApiUser>> {
            override fun subscribe(e: ObservableEmitter<MutableList<ApiUser>>) {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getApiUserList())
                    e.onComplete()
                }
            }
        })
    }

    private fun getObserver(): Observer<MutableList<User>> {
        return object : Observer<MutableList<User>> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed())
            }

            override fun onNext(userList: MutableList<User>) {
                textView!!.append(" onNext")
                textView!!.append(AppConstant.LINE_SEPARATOR)
                for (user in userList) {
                    textView!!.append(" firstname : " + user.firstname)
                    textView!!.append(AppConstant.LINE_SEPARATOR)
                }
                Log.d(TAG, " onNext : " + userList.size)
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
        private val TAG = MapExampleActivity::class.java.simpleName
    }
}