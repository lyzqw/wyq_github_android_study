//package com.qwlyz.androidstudy.fragment
//
//import android.text.SpannableStringBuilder
//import android.text.Spanned
//import android.text.TextPaint
//import android.text.method.LinkMovementMethod
//import android.text.style.ClickableSpan
//import android.view.View
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import kotlinx.android.synthetic.main.fragment_topic.*
//import java.util.*
//import java.util.regex.Pattern
//import kotlin.collections.ArrayList
//
///**
// *
// * @author lyz
// */
//class TopicAtFragment : BaseFragment() {
//    val contentOri = "88..3331122111hhhh aaa#bbb#ccctest@unicode#  sss 555#666#fff@ klj"
//    val content = "\u200b" + contentOri + "\u200b"
//
//    override fun getLayoutId(): Int  = R.layout.fragment_topic
//
//    override fun initData() {
//        start.setOnClickListener {
////            val handleRTLTopic = handleRTLTopic(handleRTLAt())
////            text_topic.text = handleRTLTopic
//
//            text_topic.movementMethod = LinkMovementMethod.getInstance()
//            text_topic.text = handleRTLSpanText()
//        }
//    }
//
//    private fun handleRTLTopic(builder: SpannableStringBuilder): SpannableStringBuilder {
//        val text = "[^\\s]+?#"
//        val p = Pattern.compile(text)
//        val m = p.matcher(content)
//        val topicList: MutableList<String> = ArrayList()
//        while (m.find()) {
//            var group = m.group()
//            if (group.contains("@")) {
//                group = group.subSequence(group.lastIndexOf("@") + 1, group.length).toString()
//            }
//            topicList.add(group)
//        }
//
//        var endIndex = content.length
//        for (i in topicList.indices.reversed()) {
//            val topic = topicList[i]
//            val startIndex = content.lastIndexOf(topic, endIndex)
//            endIndex = startIndex + topic.length
//            if (startIndex == -1) {
//                continue
//            }
//            builder.setSpan(object : ClickableSpan() {
//                override fun onClick(widget: View) {
//                    println(topic)
//                }
//                override fun updateDrawState(ds: TextPaint) {
//                    ds.color = resources.getColor(android.R.color.holo_purple)
//                }
//            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        return builder
//    }
//    private fun handleRTLAt(): SpannableStringBuilder {
//        val topicList: MutableList<String> = ArrayList()
//        val atList = setAtList(content)
//        topicList.addAll(atList)
//        println("topicList: "+topicList)
//        val builder = SpannableStringBuilder(content)
//        var endIndex = content.length
//        for (i in topicList.indices.reversed()) {
//            val topic = topicList[i]
//            val startIndex = content.lastIndexOf(topic, endIndex)
//            endIndex = startIndex + topic.length
//            if (startIndex == -1) {
//                continue
//            }
//            builder.setSpan(object : ClickableSpan() {
//                override fun onClick(widget: View) {
//                    println(topic)
//                }
//                override fun updateDrawState(ds: TextPaint) {
//                    ds.color = resources.getColor(android.R.color.holo_purple)
//                }
//            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        return builder
//    }
//
//    fun setAtList(content: String): MutableList<String> {
//        val topic: MutableList<String> = ArrayList()
//        val text = "[^\\s]+?@"
//        val p = Pattern.compile(text)
//        val m = p.matcher(content)
//        while (m.find()) {
//            var group = m.group()
//            if (group.contains("#")) {
//                group = group.subSequence(group.lastIndexOf("#") + 1, group.length).toString()
//            }
//            topic.add(group)
//        }
//        return topic
//    }
//
//
//    private fun handleTopic(): SpannableStringBuilder {
//        val content = "#aaa #bbb @ccc test #aaa unicode"
//        val text = "#[^\\s]+"
//        val p = Pattern.compile(text)
//        val m = p.matcher(content)
//        val topicList: MutableList<String> =
//            ArrayList()
//        while (m.find()) {
//            val group = m.group()
//            topicList.add(group)
//        }
//        println(Arrays.toString(topicList.toTypedArray()))
//        val builder = SpannableStringBuilder(content)
//        var endIndex = 0
//        for (i in topicList.indices) {
//            val topic = topicList[i]
//            val startIndex = content.indexOf(topic, endIndex)
//            endIndex = startIndex + topic.length
//            if (startIndex == -1) {
//                continue
//            }
//            builder.setSpan(object : ClickableSpan() {
//                override fun onClick(widget: View) {
//                    println(topic)
//                }
//                override fun updateDrawState(ds: TextPaint) {
//                    ds.color = resources.getColor(android.R.color.holo_purple)
//                }
//            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//
//        return builder
//    }
//
//    /**
//     * 只适配RTL
//     */
//    private fun handleRTLSpanText(): SpannableStringBuilder {
//        val tags: MutableList<String> = java.util.ArrayList()
//        val content = "موسيقية#عربية#سعودية#مصرية #سوريا #سودانية#الأغاني"
//        val split = content.split("@|#| ".toRegex())
//        val tagsList: MutableList<String> = LinkedList()
//        tags.add("#")
//        tags.add("@")
//        for (i in split.indices) {
//            val splitContent = split[i]
//            if (!hasSymbol(tags, content, splitContent)) {
//                continue
//            }
//            val symbol = getSymbol(content, splitContent)
//            println("symbol $symbol")
//            tagsList.add(symbol.toString() + splitContent)
//        }
//        val builder = SpannableStringBuilder(content)
//        var endIndex = content.length
//        for (i in tagsList.indices.reversed()) {
//            val topic = tagsList[i]
//            val startIndex = content.lastIndexOf(topic, endIndex)
//            endIndex = startIndex + topic.length
//            if (startIndex == -1) {
//                continue
//            }
//            builder.setSpan(object : ClickableSpan() {
//                override fun onClick(widget: View) {
//                    println(topic)
//                }
//                override fun updateDrawState(ds: TextPaint) {
//                    ds.color = resources.getColor(android.R.color.holo_purple)
//                }
//            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        return builder
//    }
//
//    private fun hasSymbol(
//        tags: List<String>,
//        content: String,
//        splitContent: String
//    ): Boolean {
//        if (splitContent.isEmpty()) return false
//        try {
//            val format = getSymbol(content, splitContent)
//            if (tags.contains(format.toString() + "")) {
//                return true
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return false
//    }
//
//    private fun getSymbol(content: String, splitContent: String): Char {
//        try {
//            return content[content.lastIndexOf(splitContent) - 1]
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return ' '
//    }
//}