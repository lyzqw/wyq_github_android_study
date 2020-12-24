package com.qwlyz.androidstudy

import com.qwlyz.androidstudy.fragment.IndicatorSeekBarFragment
import com.qwlyz.androidstudy.fragment.PopupWindowFragment
import java.io.Serializable


enum class PageWidget(val title: String, val pageWidgetClass: Class<*>) : Serializable {

    PopupWindowWidget("更新popupWindow学习", PopupWindowFragment::class.java),
    IndicatorSeekBarWidget("自定义IndicatorSeekBar", IndicatorSeekBarFragment::class.java)

}