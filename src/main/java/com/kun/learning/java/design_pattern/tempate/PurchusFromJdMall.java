package com.kun.learning.java.design_pattern.tempate;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/6 19:11
 */
public class PurchusFromJdMall extends Purchus {
    @Override
    protected void addThing2PurchusCar() {
        System.out.println("add thing to jd purchus car");
    }

    @Override
    protected void submit() {
        System.out.println("Ok, go to submit platform");
    }

    @Override
    protected void pay() {
        System.out.println("pay using jd pay");
    }
}
