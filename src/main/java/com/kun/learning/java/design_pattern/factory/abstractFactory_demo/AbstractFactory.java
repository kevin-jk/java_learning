package com.kun.learning.java.design_pattern.factory.abstractFactory_demo;


import com.kun.learning.java.design_pattern.common.Vehicle;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 16:35
 *
 * 工厂方法是特殊的抽象工厂方法
 *
 * 抽象工厂方法中可以生成不同的产品
 *
 * https://www.cnblogs.com/qiaoconglovelife/p/5750290.html
 */
public interface AbstractFactory {

  Vehicle create();
}
