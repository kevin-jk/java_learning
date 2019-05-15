package com.kun.learning.java.design_pattern.factory.factorymethod_demo;

import com.kun.learning.java.design_pattern.common.Bus;
import com.kun.learning.java.design_pattern.common.Car;
import com.kun.learning.java.design_pattern.common.Vehicle;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 16:29
 *
 * 工厂方法模式： 為每一个具体的产品提供一个工厂
 */
public interface FactoryMethod {
    Vehicle create();
}
