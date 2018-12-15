package com.kun.learning.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2018/10/19
 */
public class UserServiceProxy implements InvocationHandler {
     private IUserService iUserService;
     UserServiceProxy(IUserService service){
         this.iUserService = service;
     }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       System.out.println("UserServiceProxy, do other things");
       return method.invoke(iUserService,args);
    }
}
