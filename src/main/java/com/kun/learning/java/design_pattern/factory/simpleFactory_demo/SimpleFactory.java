package com.kun.learning.java.design_pattern.factory.simpleFactory_demo;

import com.kun.learning.java.design_pattern.common.Bus;
import com.kun.learning.java.design_pattern.common.Car;
import com.kun.learning.java.design_pattern.common.Vehicle;
import com.kun.learning.java.design_pattern.observe.Event;
import com.kun.learning.java.design_pattern.observe.Listener;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/5 10:57
 * <p>
 * 簡單工廠模式（靜態工廠方法）：通过标识符来创建具体类
 */
public class SimpleFactory implements Event {
    public enum VehicleEnum {
        CAR,
        BUS,
        ;
    }


    private List<Listener> listenerMap = new CopyOnWriteArrayList<>();


    public Vehicle getDetailVehicle(VehicleEnum vehicleEnum) {
        switch (vehicleEnum) {
            case BUS:

                Vehicle bus = new Bus();
                onEvent(bus);
                return bus;
            case CAR:
                Vehicle car = new Car();
                onEvent(car);
                return car;
            default:
                return null;
        }
    }

    @Override
    public void registerListener(Listener listener) {
        listenerMap.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listenerMap.remove(listener);
    }

    @Override
    public void onEvent(Object param) {
        listenerMap.forEach(r -> r.onEventNotify(param));
    }
}
