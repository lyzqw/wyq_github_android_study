package com.qwlyz.androidstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TViewModel : ViewModel() {
    fun testLoad(data: String = "1") {
        dataLiveData.value = data
    }

    val dataLiveData = MutableLiveData<String>()


}