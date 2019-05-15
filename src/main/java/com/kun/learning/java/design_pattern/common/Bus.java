package com.kun.learning.java.design_pattern.common;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 10:55
 */
public class Bus implements Vehicle{
    @Override
    public void drive() {
        System.out.println("只能当公交车司机了");
    }
}
