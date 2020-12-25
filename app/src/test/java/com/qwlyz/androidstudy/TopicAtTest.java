package com.qwlyz.androidstudy;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyz
 */
class TopicAtTest {

    public static final char INVISIBLE_CHAR = '\u200b';

    public static void test() {
        String content = "#aaa#bbb @ccc test unicode";
//        String contentOri="#aaa#bbb @ccc test unicode";
//        String content="\u200b"  + contentOri + "\u200b" ;
        String[] split = content.split("@|#| ");
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.endsWith(String.valueOf(INVISIBLE_CHAR))) {
                int index = getIndex(i, split) - 1;
                char afterChar = content.charAt(index > content.length() - 1 ? content.length() - 1 : index);
                temp.append(s).append(afterChar);
                if (i < split.length - 1) {
                    continue;
                } else {
                    s = temp.toString();
                    temp.setLength(0);
                }
            } else if (!temp.toString().equals("")) {
                s = temp.append(s).toString();
                temp.setLength(0);
            }
            if (s.trim().equals("")) {
                continue;
            }
            int index = content.indexOf(s);
            /*zune: 这里补上前面split的@# **/
            if (index > 0) {
                index--;
                s = content.charAt(index) + s;
            }
            if (s.startsWith("#")) {
                String finalS = s;
                System.out.println("开始设置span: " + finalS.substring(1).replaceAll(String.valueOf(INVISIBLE_CHAR), ""));
//                content.setSpan(new ClickableSpan() {
//                    @Override
//                    public void onClick(@NonNull View widget) {
//                        if (onShowExpand != null) {
//                            onShowExpand.onClickTopic();
//                        }
//                    }
//
//                    @Override
//                    public void updateDrawState(TextPaint ds) {
//                        ds.setColor(ds.getColor());
//                        ds.setUnderlineText(false);
//                    }
//                }, index, index + s.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            } else if (s.startsWith("@")) {
                String finalS = s;
                System.out.println("开始设置@");
//                content.setSpan(new ClickableSpan() {
//                    @Override
//                    public void onClick(@NonNull View widget) {
//                        if (onShowExpand != null) {
//                            onShowExpand.onClickUser(finalS.substring(1).replaceAll(String.valueOf(INVISIBLE_CHAR), ""));
//                        }
//                    }
//
//                    @Override
//                    public void updateDrawState(TextPaint ds) {
//                        ds.setColor(ds.getColor());
//                        ds.setUnderlineText(false);
//                    }
//                }, index, index + s.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            while (index != -1) {
                index = content.toString().indexOf(s, index + 1);
            }
        }
    }

    public static void testRTL() {
        String content = "aaa# bbb# @ccc test unicode";
        String text = "[^\\s]+#";
        Pattern p = Pattern.compile(text);
        Matcher m = p.matcher(content);
        List<String> topicList = new ArrayList<>();
        while (m.find()) {
            String group = m.group();
            topicList.add(group);
        }
        System.out.println(Arrays.toString(topicList.toArray()));
        int endIndex = content.length();
        for (int i = topicList.size() - 1; i >= 0; i--) {
            String topic = topicList.get(i);
            int startIndex = content.lastIndexOf(topic, endIndex);
            endIndex = startIndex + topic.length();
            if (startIndex == -1) {
                continue;
            }
            System.out.println(topic +":start "+startIndex +" ==end "+endIndex);
        }

//        int endIndex = 0;
//        for (int i = 0; i < topicList.size(); i++) {
//            String topic = topicList.get(i);
//            int startIndex = content.indexOf(topic, endIndex);
//            endIndex = startIndex + topic.length();
//            if (startIndex == -1) {
//                continue;
//            }
//            System.out.println(topic +":start "+startIndex +" ==end "+endIndex);
//        }
    }

    private static int getIndex(int position, String[] split) {
        int index = 0;
        for (int i = 0; i < split.length; i++) {
            index += split[i].length();
            index++;
            if (position == i) {
                return index;
            }
        }
        return index;
    }
}
