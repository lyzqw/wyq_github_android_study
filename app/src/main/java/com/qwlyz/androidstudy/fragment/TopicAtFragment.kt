package com.qwlyz.androidstudy.fragment

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import kotlinx.android.synthetic.main.fragment_topic.*
import java.util.*
import java.util.regex.Pattern

/**
 *
 * @author lyz
 */
class TopicAtFragment : BaseFragment() {

    override fun getLayoutId(): Int  = R.layout.fragment_topic

    override fun initData() {
        start.setOnClickListener {
            text_topic.text = handleRTLTopic()
        }
        text_topic.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun handleRTLTopic(): SpannableStringBuilder {
        val content = "llll aaa#fk bbb#sjlfj ccc@sldfkj test unicode"
        val text = "[^\\s]+#"
        val p = Pattern.compile(text)
        val m = p.matcher(content)
        val topicList: MutableList<String> = ArrayList()
        while (m.find()) {
            val group = m.group()
            topicList.add(group)
        }
        setAtList(content,topicList)

        val builder = SpannableStringBuilder(content)
        var endIndex = content.length
        for (i in topicList.indices.reversed()) {
            val topic = topicList[i]
            val startIndex = content.lastIndexOf(topic, endIndex)
            endIndex = startIndex + topic.length
            if (startIndex == -1) {
                continue
            }
            builder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    println(topic)
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = resources.getColor(android.R.color.holo_purple)
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return builder
    }

    fun setAtList(content: String, topic: MutableList<String>) {
        val text = "[^\\s]+@"
        val p = Pattern.compile(text)
        val m = p.matcher(content)
        while (m.find()) {
            val group = m.group()
            topic.add(group)
        }
    }


    private fun handleTopic(): SpannableStringBuilder {
        val content = "#aaa #bbb @ccc test #aaa unicode"
        val text = "#[^\\s]+"
        val p = Pattern.compile(text)
        val m = p.matcher(content)
        val topicList: MutableList<String> =
            ArrayList()
        while (m.find()) {
            val group = m.group()
            topicList.add(group)
        }
        println(Arrays.toString(topicList.toTypedArray()))
        val builder = SpannableStringBuilder(content)
        var endIndex = 0
        for (i in topicList.indices) {
            val topic = topicList[i]
            val startIndex = content.indexOf(topic, endIndex)
            endIndex = startIndex + topic.length
            if (startIndex == -1) {
                continue
            }
            builder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    println(topic)
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = resources.getColor(android.R.color.holo_purple)
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        return builder
    }

}