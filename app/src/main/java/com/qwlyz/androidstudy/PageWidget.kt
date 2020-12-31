package com.qwlyz.androidstudy

import com.qwlyz.androidstudy.fragment.*
import java.io.Serializable


enum class PageWidget(val title: String, val pageWidgetClass: Class<*>) : Serializable {

    PopupWindowWidget("更新popupWindow学习", PopupWindowFragment::class.java),
    IndicatorSeekBarWidget("自定义IndicatorSeekBar", IndicatorSeekBarFragment::class.java),
    RTLTopicAtWidget("RTL话题", TopicAtFragment::class.java),
    IOSConverterAndroid("ios文件转Android", IosConverterAndroidFragment::class.java),
    FLexBoxWidget("横向滚动的流式布局", FlexBoxFragment::class.java)

}