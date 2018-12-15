package com.kun.learning.java.proxy;

/**
 * Created by jrjiakun on 2018/10/19
 */
public class UserServiceProxy1 extends JDKProxy{
    public UserServiceProxy1(Object target){
        super(target);
    }
    @Override
    protected void before() {
        System.out.println("Before");
    }

    @Override
    protected void after() {
        System.out.println("after");
    }
}
