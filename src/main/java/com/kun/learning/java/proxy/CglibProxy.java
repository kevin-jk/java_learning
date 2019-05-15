package com.kun.learning.java.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/25 10:05
 */
public abstract class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(enhancer, args);
        end();
        return result;
    }

    protected abstract void before();

    protected abstract void end();


    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
