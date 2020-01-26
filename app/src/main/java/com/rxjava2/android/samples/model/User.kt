package com.rxjava2.android.samples.model

/**
 * Created by amitshekhar on 27/08/16.
 */
class User {
    var id: Long = 0
    var firstname: String? = null
    var lastname: String? = null
    var isFollowing = false

    constructor() {}
    constructor(apiUser: ApiUser?) {
        id = apiUser!!.id
        firstname = apiUser.firstname
        lastname = apiUser.lastname
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isFollowing=" + isFollowing +
                '}'
    }

    override fun hashCode(): Int {
        return id as Int + firstname!!.hashCode() + lastname!!.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is User) {
            val user = obj as User?
            return id == user!!.id && firstname == user.firstname && lastname == user.lastname
        }
        return false
    }
}