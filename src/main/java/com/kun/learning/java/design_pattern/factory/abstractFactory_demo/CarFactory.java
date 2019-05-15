package com.kun.learning.java.design_pattern.factory.abstractFactory_demo;

import com.kun.learning.java.design_pattern.common.Car;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 16:32
 *
 * 工厂方法： 通过具体的工厂类来创建具体的产品
 */
public class CarFactory  implements AbstractFactory{

    @Override
    public Car create() {
        return new Car();
    }
}
