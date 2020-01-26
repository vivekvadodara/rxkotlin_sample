package com.rxjava2.android.samples.utils

import android.util.Log
import com.androidnetworking.error.ANError
import com.rxjava2.android.samples.model.ApiUser
import com.rxjava2.android.samples.model.User
import java.util.*

/**
 * Created by amitshekhar on 27/08/16.
 */
object Utils {
    fun getUserList(): MutableList<User> {
        val userList: MutableList<User> = ArrayList()
        val userOne = User()
        userOne.firstname = "Amit"
        userOne.lastname = "Shekhar"
        userList.add(userOne)
        val userTwo = User()
        userTwo.firstname = "Manish"
        userTwo.lastname = "Kumar"
        userList.add(userTwo)
        val userThree = User()
        userThree.firstname = "Sumit"
        userThree.lastname = "Kumar"
        userList.add(userThree)
        return userList
    }

    fun getApiUserList(): MutableList<ApiUser> {
        val apiUserList: MutableList<ApiUser> = ArrayList()
        val apiUserOne = ApiUser()
        apiUserOne.firstname = "Amit"
        apiUserOne.lastname = "Shekhar"
        apiUserList.add(apiUserOne)
        val apiUserTwo = ApiUser()
        apiUserTwo.firstname = "Manish"
        apiUserTwo.lastname = "Kumar"
        apiUserList.add(apiUserTwo)
        val apiUserThree = ApiUser()
        apiUserThree.firstname = "Sumit"
        apiUserThree.lastname = "Kumar"
        apiUserList.add(apiUserThree)
        return apiUserList
    }

    fun convertApiUserListToUserList(apiUserList: MutableList<ApiUser>): MutableList<User> {
        val userList: MutableList<User> = ArrayList()
        for (apiUser in apiUserList!!) {
            val user = User()
            user.firstname = apiUser!!.firstname
            user.lastname = apiUser.lastname
            userList.add(user)
        }
        return userList
    }

    fun convertApiUserListToApiUserList(apiUserList: MutableList<ApiUser>): MutableList<ApiUser> {
        return apiUserList
    }

    fun getUserListWhoLovesCricket(): MutableList<User> {
        val userList: MutableList<User> = ArrayList()
        val userOne = User()
        userOne.id = 1
        userOne.firstname = "Amit"
        userOne.lastname = "Shekhar"
        userList.add(userOne)
        val userTwo = User()
        userTwo.id = 2
        userTwo.firstname = "Manish"
        userTwo.lastname = "Kumar"
        userList.add(userTwo)
        return userList
    }

    fun getUserListWhoLovesFootball(): MutableList<User> {
        val userList: MutableList<User> = ArrayList()
        val userOne = User()
        userOne.id = 1
        userOne.firstname = "Amit"
        userOne.lastname = "Shekhar"
        userList.add(userOne)
        val userTwo = User()
        userTwo.id = 3
        userTwo.firstname = "Sumit"
        userTwo.lastname = "Kumar"
        userList.add(userTwo)
        return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: MutableList<User>, footballFans: MutableList<User>): MutableList<User> {
        val userWhoLovesBoth: MutableList<User> = ArrayList()
        for (cricketFan in cricketFans!!) {
            for (footballFan in footballFans!!) {
                if (cricketFan!!.id == footballFan!!.id) {
                    userWhoLovesBoth.add(cricketFan)
                }
            }
        }
        return userWhoLovesBoth
    }

    fun logError(TAG: String?, e: Throwable?) {
        if (e is ANError) {
            val anError = e as ANError?
            if (anError!!.getErrorCode() != 0) { // received ANError from server
// error.getErrorCode() - the ANError code from server
// error.getErrorBody() - the ANError body from server
// error.getErrorDetail() - just a ANError detail
                Log.d(TAG, "onError errorCode : " + anError.getErrorCode())
                Log.d(TAG, "onError errorBody : " + anError.getErrorBody())
                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail())
            } else { // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail())
            }
        } else {
            Log.d(TAG, "onError errorMessage : " + e!!.message)
        }
    }
}