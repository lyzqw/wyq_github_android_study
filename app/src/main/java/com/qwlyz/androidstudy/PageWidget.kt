package com.qwlyz.androidstudy

import java.io.Serializable


enum class PageWidget(val title: String, val pageWidgetClass: Class<*>) : Serializable {

    PopupWindowWidget("更新popupWindow学习", PopupWindowFragment::class.java)

}