package com.rxjava2.android.samples.model

/**
 * Created by amitshekhar on 27/08/16.
 */
class ApiUser {
    var id: Long = 0
    var firstname: String? = null
    var lastname: String? = null
    override fun toString(): String {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}'
    }
}