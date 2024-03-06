package com.qwlyz.androidstudy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TViewModel : ViewModel() {
    fun testLoad(data: String = "1") {
        dataLiveData.value = data
    }

    val dataLiveData = MutableLiveData<String>()


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG_LIFE, "onCleared: ")
    }
}