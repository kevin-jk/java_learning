package com.kun.learning.java.proxy;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/25 10:10
 */
public class UserServiceCglibProxy extends CglibProxy {

    @Override
    protected void before() {
        System.out.println("Proxy before");
    }

    @Override
    protected void end() {
        System.out.println("Proxy end");
    }
}
