package com.yuwq.libs_common

import android.content.Context
import android.view.View


interface Provider {

    fun getContext(source: Any?): Context?

    fun findView(source: Any?, id: Int): View?}