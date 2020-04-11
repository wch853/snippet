package com.wch.snippet.regex;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegexDemo {

    /**
     * Pattern
     */
    @Test
    public void test1() {
        String regex = "https://github\\.com/(\\w+)/(.*)";
        String text = "https://github.com/wch853/jianshu";

        boolean isMatch = Pattern.matches(regex, text);
        System.out.println(isMatch);
    }

    /**
     * Pattern Matcher
     */
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

    /**
     * 懒惰模式与贪心模式
     */
    @Test
    public void test3() {

        String text = "src=\"1.jpg\" width=\"100px\"";

        // 后面有 ? 表示懒惰模式，匹配尽量短的字符串
        String lazyRegex = "src=\"(.+?)\"";

        Pattern lazyPattern = Pattern.compile(lazyRegex);
        Matcher lazyMatcher = lazyPattern.matcher(text);
        if (lazyMatcher.find()) {
            System.out.println(lazyMatcher.group());    // src="1.jpg"
            System.out.println(lazyMatcher.group(1));   // 1.jpg
        }

        // 没有 ? ，会匹配尽量长的字符串
        String greedyRegex = "src=\"(.+)\"";

        Pattern greedyPattern = Pattern.compile(greedyRegex);
        Matcher greedyMatcher = greedyPattern.matcher(text);
        if (greedyMatcher.find()) {
            System.out.println(greedyMatcher.group());  // src="1.jpg" width="100px"
            System.out.println(greedyMatcher.group(1)); // 1.jpg" width="100px
        }
    }
}
