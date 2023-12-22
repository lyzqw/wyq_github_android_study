package com.qwlyz.androidstudy

import com.qwlyz.androidstudy.fragment.*
import java.io.Serializable


enum class PageWidget(val title: String, val pageWidgetClass: Class<*>) : Serializable {

    HorizontalScrollWidget("在滚动布局里切换居中", HorizontalScrollFragment::class.java),
    PopupWindowWidget("更新popupWindow学习", PopupWindowFragment::class.java),
    ViewModelWidget("更新viewmodel学习", ViewModelFragment::class.java),
    PermissionDialogWidget("PermissionDialog", PermissionDialogFragment::class.java),
    SocketWidget("socket", SocketFragment::class.java),
    XlogWidget("xlog", XlogFragment::class.java),
    Coordinator("Coordinator", CoordinatorLayoutFragment::class.java),
//    GlideImageWidget("GlideImage学习", GlideImageFragment::class.java),
//    IndicatorSeekBarWidget("自定义IndicatorSeekBar", IndicatorSeekBarFragment::class.java),
//    RTLTopicAtWidget("RTL话题", TopicAtFragment::class.java),
    Retrofit2("学习retofit", Retrofit2Fragment::class.java),
//    IOSConverterAndroid("ios文件转Android", IosConverterAndroidFragment::class.java),
//    Android11Storage("Android11存储", Android11StorageFragment::class.java),
//    StartActivity("activity_跳转", StartActivityFragment::class.java),
//    FLexBoxWidget("横向滚动的流式布局", FlexBoxFragment::class.java),

}