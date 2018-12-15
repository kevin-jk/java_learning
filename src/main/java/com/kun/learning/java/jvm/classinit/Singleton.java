package com.kun.learning.java.jvm.classinit;

/**
 * Created by jrjiakun on 2018/11/30
 *
 * 验证类的加载，链接和初始化
 *
 * 查看类加载过程, 配置参数： 1、 -XX:+TraceClassLoading  或者 2. -verbose:class
 *
 * 验证java类的链接过程： 通过修改字节码来实现
 *
 *
 $ java -cp /path/to/asmtools.jar org.openjdk.asmtools.jdis.Main Singleton\$LazyHolder.class > Singleton\$LazyHolder.jasm.1
 $ awk 'NR==1,/stack 1/{sub(/stack 1/, "stack 0")} 1' Singleton\$LazyHolder.jasm.1 > Singleton\$LazyHolder.jasm
 $ java -cp /path/to/asmtools.jar org.openjdk.asmtools.jasm.Main Singleton\$LazyHolder.jasm
 $ java -verbose:class Singleton

 *
 */
public class Singleton {
    private Singleton() {}
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }
    public static Object getInstance(boolean flag) {
        //新建数组会导致LazyHolder类的加载， 但是不会链接和初始化
        if (flag) return new LazyHolder[2];
        // 此时才会真正的链接和初始化
        return LazyHolder.INSTANCE;
    }
    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }
}

