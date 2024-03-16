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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyz
 */
class TopicAtTest {

    public static final char INVISIBLE_CHAR = '\u200b';

    int l,r=0;

    public static void test() {
        int[] ans = new int[3];
        int[] ans2 = new int[ans.length];

        String content = "88..3331122111hhhh aaa#bbb#ccctest@unicode#  sss 555#666#fff@ klj";
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
        List<String> tags = new ArrayList<>();
        String content = "موسيقية#عربية#سعودية#مصرية #سوريا #سودانية#الأغاني";
        System.out.println(isProbablyArabic("11موسيقي"));
        String[] split = content.split("@|#| ");

        List<String> tagsList = new LinkedList<>();
        tags.add("#");
        tags.add("@");
        for (int i = 0; i < split.length; i++) {
            String splitContent = split[i];
            if (!hasSymbol(tags, content, splitContent)) {
                continue;
            }
            char symbol = getSymbol(content, splitContent);
            System.out.println("symbol : "+symbol);
            tagsList.add(symbol + splitContent);
        }

        System.out.println(Arrays.toString(tagsList.toArray()));
        int endIndex = content.length();
        for (int i = tagsList.size() - 1; i >= 0; i--) {
            String topic = tagsList.get(i);
            int startIndex = content.lastIndexOf(topic, endIndex);
            endIndex = startIndex + topic.length();
            if (startIndex == -1) {
                continue;
            }
            System.out.println(topic + ":start " + startIndex + " ==end " + endIndex);
        }
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

    private static boolean hasSymbol(List<String> tags, String content, String splitContent) {
        if (splitContent.isEmpty()) return false;
        try {
            char format = getSymbol(content, splitContent);
            if (tags.contains(format + "")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static char getSymbol(String content, String splitContent) {
        try {
            return content.charAt(content.lastIndexOf(splitContent) - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ' ';
    }

    public static boolean isProbablyArabic(String s) {
        for (int i = 0; i < s.length();) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0)
                return true;
            i += Character.charCount(c);
        }
        return false;
    }

}
