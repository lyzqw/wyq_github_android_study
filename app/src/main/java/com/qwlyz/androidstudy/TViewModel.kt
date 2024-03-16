package com.qwlyz.androidstudy

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.HandlerDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

class TViewModel : ViewModel() {

    val receive = MediatorLiveData<String>()
    val send1 = MutableLiveData<String>()
    val send2 = MutableLiveData<String>()
    val send3 = MutableLiveData<String>()


    fun testLoad(data: String = "1") {
        dataLiveData.value = data
        viewModelScope.launch(context = Dispatchers.Main.immediate) {

        }
        viewModelScope.launch(context = Dispatchers.IO) {
            delay(1)
        }
//        val drawable = GradientDrawable()
//        drawable.gradientType = GradientDrawable.LINEAR_GRADIENT
//        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            drawable.setColors(colors, offsets)
//        }
    }


    val dataLiveData = MutableLiveData<String>()


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG_LIFE, "onCleared: ")
    }

    fun <T> launch3(block: (suspend () -> T)) {
        // 1、传入代码块block，使用block创建协程，
        // 2、同时自行创建一个续体，「resumeWith」最终会被调用
        val coroutine = block.createCoroutine(object : Continuation<T> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<T>) {
                println("\nresumeWith=$result")
            }
        })
        // 3、执行block协程
        coroutine.resume(Unit)
    }
}