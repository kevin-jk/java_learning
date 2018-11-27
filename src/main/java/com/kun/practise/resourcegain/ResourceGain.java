package com.kun.practise.resourcegain;

/**
 * Created by jrjiakun on 2018/11/15
 *
 * @link https://www.cnblogs.com/macwhirr/p/8116583.html
 *
 */
public class ResourceGain {
    public static void main(String[] agsr) {

        getPath();
    }

    public static void getPath() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader.getResource("").getPath());
        // 不能以/ 开头
       // System.out.println(loader.getResource("/").getPath());
        System.out.println(ResourceGain.class.getResource("").getPath());
        System.out.println(ResourceGain.class.getResource("/").getPath());
        System.out.println(System.getProperty("user.dir"));
    }


}
