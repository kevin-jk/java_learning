package com.kun.practise.regex;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jrjiakun on 2018/11/6
 */
public class RegexDemo {

    private static Pattern passwordP = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()])(?!.*kevin).{8,16}");

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.printf("%nEnter your regex: ");
//            Pattern pattern = Pattern.compile(scanner.nextLine());
//            System.out.printf("Enter input string to search: ");
//           String data =  scanner.nextLine();
//            passwordMatch(data);
//            if (passwordMatch(data)) {
//                System.out.println("true");
//            }else{
//                System.out.println("false");
//            }
//            Matcher matcher = pattern.matcher(scanner.nextLine());
//            boolean found = false;
//            while (matcher.find()) {
//                System.out.printf(
//                        "I found the text \"%s\" starting at index %d and ending at index %d.%n",
//                        matcher.group(), matcher.start(), matcher.end()
//                );
//                found = true;
//            }
//            if (!found) {
//                System.out.printf("No match found.%n");
//            }
//        }
//        String rule1="content:\".+\"";    //贪婪模式
//        String rule2="content:\".+?\"";    //非贪婪模式
//        String text="(content:\"rcpt to root\",pcre:\"word\";)";
//        System.out.println("文本："+text);
//        System.out.println("贪婪模式："+rule1);
//        Pattern p1 =Pattern.compile(rule1);
//        Matcher m1 = p1.matcher(text);
//        while(m1.find()){
//            System.out.println("匹配结果："+m1.group(0));
//        }
//
//        System.out.println("非贪婪模式："+rule2);
//        Pattern p2 =Pattern.compile(rule2);
//        Matcher m2 = p2.matcher(text);
//        while(m2.find()){
//            int i=0;
//
//            System.out.println("匹配结果："+m2.group(i));
//            i++;
//        }
//        positiveLookAhead();
        //    negativeLookaheadAssertion();
        // matchQNotBehindU();
//        positiveLookbehindAssertion();
//        notSequenceString();
//        tag();
        Map map = new HashMap<String, String>();
        map.put("1", "2");
    }

    // 贪婪模式
    private void matchString(String data) {


    }
    //零断言：零宽断言是正则表达式中的一种方法，正则表达式在计算机科学中，是指一个用来描述或者匹配一系列符合某个句法规则的字符串的单个字符串
    //断言用来声明一个应该为真的事实。正则表达式中只有当断言为真时才会继续进行匹配
    // 正向先行零断言 : (?=pattern)

    /***
     *
     * 首先由正则表达式中的"^"获取控制权，首先由位置0开始进行匹配，它匹配开始位置0，匹配成功，然后控制权转交给"(?=<)"，由于"^"是零宽的，
     * 所以"(?=<)"也是从位置0处开始匹配，它要求所在的位置右侧必须是字符"<"，
     * 位置0的右侧恰好是字符"<"，匹配成功，然后控制权转交个"<",由于"(?=<)"也是零宽的，所以它也是从位置0处开始匹配，
     * 于是匹配成功，后面的匹配过程就不介绍了。
     *
     * */

    private static void positiveLookAhead() {
        System.out.println("正向先行零断言");
        String data = "<div>antzone";
        Pattern pattern = Pattern.compile("^(?=<)<[^>]+>\\w+");
        Matcher m = pattern.matcher(data);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    //零宽负向先行断言:(?!pattern)
    private static void negativeLookaheadAssertion() {

    }

    //零宽正向后行断言:(?<=pattern)
    private static void positiveLookbehindAssertion() {
        System.out.println("零宽正向后行断言");
        String data = "I am reading";
        Pattern pattern = Pattern.compile("(?<=\\bre)\\w+\\b");
        Matcher m = pattern.matcher(data);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    //零宽负向后行断言:(?<!pattern)
    private static void negativeLookbehindAssertion() {

    }

    // 匹配单词中间的q, 但是q后不能为u, q后面必须有字母
    private static void matchQNotBehindU() {
        // 该正则有问题
        Pattern p = Pattern.compile("\\b\\w+q[^u]\\w+\\b");
        //Pattern p  = Pattern.compile("\\b\\w+q(?!u)\\w+\\b");
        String data = "Hello, Iraq fighting, quality, with a aqzestion.";
        Matcher m = p.matcher(data);
        while (m.find()) {
            System.out.println(m.group());
        }
    }


    //字符串中不能出现kevin
    private static void notSequenceString() {
        Pattern p = Pattern.compile("\\b((?!kevin)\\w)+\\b");
        String data = "hljkjkevinlkfad";
        Matcher m = p.matcher(data);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    // 密码校验
    private static boolean passwordMatch(String data) {


        Matcher m = passwordP.matcher(data);
        while (m.find()) {
            System.out.println(m.group());
            for (int i = 1; i < m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
        }
        return m.matches();
    }

    public static void tag() {
        // 分析如下结果产生的原因
        Pattern p = Pattern.compile("<.*?>(.+?)</.*>");
        Matcher m = p.matcher("<div style=color:#00FF00>  <h3>This is a header</h3>  <p>This is a paragraph.</p></div>");
        while (m.find()) {
            System.out.println(m.group());
            for (int i = 1; i <= m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
        }
    }

    {


    }
}
