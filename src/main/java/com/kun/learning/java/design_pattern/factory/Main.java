package com.kun.learning.java.design_pattern.factory;

import com.kun.learning.java.design_pattern.common.Bus;
import com.kun.learning.java.design_pattern.common.Car;
import com.kun.learning.java.design_pattern.common.Vehicle;
import com.kun.learning.java.design_pattern.factory.simpleFactory_demo.SimpleFactory;
import com.kun.learning.java.design_pattern.observe.Listener;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 11:00
 */
public class Main implements Listener {
    public static void main(String[] args) {
        Main main = new Main();
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.registerListener(main);
        Vehicle vehicle = simpleFactory.getDetailVehicle(SimpleFactory.VehicleEnum.BUS);
        vehicle.drive();
    }

    @Override
    public void onEventNotify(Object param) {
        if(param instanceof Car){
            System.out.println("Car create complished");
            return;
        }
        if(param instanceof Bus){
            System.out.println("Bus create complished");
            return;
        }
    }
}
