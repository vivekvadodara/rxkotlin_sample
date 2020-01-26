package com.rxjava2.android.samples.model

/**
 * Created by amitshekhar on 04/02/17.
 */
class UserDetail {
    var id: Long = 0
    var firstname: String? = null
    var lastname: String? = null
    override fun toString(): String {
        return "UserDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}'
    }
}