package com.kun.learning.java.design_pattern.factory.factorymethod_demo;

import com.kun.learning.java.design_pattern.common.Car;
import com.kun.learning.java.design_pattern.common.Vehicle;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/6 19:04
 */
public class CarFactory implements FactoryMethod{
    @Override
    public Vehicle create() {
        return new Car();
    }
}
