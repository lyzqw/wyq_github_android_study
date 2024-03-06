package com.qwlyz.androidstudy.lazy

object UserPool {

    val map = HashMap<String, User>()

    fun put(key: String, user: User) {
        map.put(key, user)
    }

    fun get(key: String) = map[key]


}