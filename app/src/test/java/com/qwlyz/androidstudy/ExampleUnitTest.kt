package com.qwlyz.androidstudy

import com.blankj.utilcode.util.ViewUtils.runOnUiThread
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun main() {
    TopicAtTest.testRTL();
//    TopicAtTest.test();
}


inline fun hello(crossinline preaction: () -> Unit, noinline post: () -> Unit): () -> Unit {
    preaction()
    println("111")
    post()
    runOnUiThread({
        preaction()
    })
    return post
}

