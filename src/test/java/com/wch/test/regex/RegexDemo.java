package com.wch.test.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    @Test
    public void test1() {
        String regex = "https://github\\.com/(\\w+)/(.*)";
        String text = "https://github.com/wch853/jianshu";

        boolean isMatch = Pattern.matches(regex, text);
        System.out.println(isMatch);
    }

    @Test
    public void test2() {
        // 通过()来建立分组
        String regex = "https://github\\.com/(\\w+)/(.*)";
        String text = "https://github.com/wch853/jianshu";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                /**
                 * 通过group(int group);来捕获组
                 * group(); / group(0); 捕获所有
                 * group(i)捕获第i个分组
                 */
                System.out.println("group:" + i + ": " + matcher.group(i));
            }
        } else {
            System.out.println("NOT FOUND");
        }
    }
}
