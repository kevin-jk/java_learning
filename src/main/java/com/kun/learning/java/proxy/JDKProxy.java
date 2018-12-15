package com.kun.learning.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jrjiakun on 2018/10/19
 */
public abstract class JDKProxy implements InvocationHandler {
    private Object target;

    private Object proxy;
    public JDKProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object ret = method.invoke(target, args);
        after();
        // proxy 就是代理对象
        if(null!=this.proxy){
            System.out.println("Check proxy:"+ (this.proxy==proxy));
        }
        return ret;
    }
    protected   abstract  void before();
    protected   abstract  void after();

}
