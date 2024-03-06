package com.qwlyz.androidstudy.lazy

import android.util.Log
import androidx.fragment.app.Fragment

class UserLazy(val key: String) : Lazy<User> {

    var cached: User? = null

    override val value: User
        get() {
            Log.d("ViewModelFragment", "cached: $cached ,this:${hashCode()}")
            val user = UserPool.get(key)
            if (user == null) {

                return User().also {
                    Log.d("ViewModelFragment", "没有缓存 ,this:${it.hashCode()}")
                    UserPool.put(key, it) }
            } else {
                Log.d("ViewModelFragment", "有缓存 ,this:${user.hashCode()}")
                return user
            }
//            return cached ?: User()
        }

    override fun isInitialized(): Boolean = cached != null

}

inline fun <reified U : Any> Fragment.v_user(): Lazy<User> {
    val key = this.javaClass.hashCode().toString()
    return UserLazy(key)
}


class User {
    var data: String = "a"
}