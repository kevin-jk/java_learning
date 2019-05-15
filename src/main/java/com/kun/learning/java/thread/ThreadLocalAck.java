package com.kun.learning.java.thread;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/4 13:42
 */
public class ThreadLocalAck {
    ThreadLocal<String> threadName = new ThreadLocal<>();

    {
        String value = Thread.currentThread().getName();
        if(value==null||"".equals(value)){
            value = "default_thread";
        }
        threadName.set(value);
    }


    public String getThreadName(){
        return threadName.get();
    }


}
