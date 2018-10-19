package com.kun.practise.proxy;

import com.kun.practise.proxy.impl.UserService;

import java.lang.reflect.Proxy;


/**
 * Created by jrjiakun on 2018/10/19
 *
 * 具体一点的原理解释：
 * @link http://note.youdao.com/noteshare?id=77a2a90627e2bbce32c6275d53ab5d25&sub=0F17E1F5FD5F4DF4B6D19D808E1723F2
 */
public class Main {

    public static void main(String[]args){
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IUserService userService = new UserService();

        String name = userService.getInfoById();
        System.out.println("Thread:"+Thread.currentThread().getName()+",name:"+name);
        UserServiceProxy1 userServiceProxy1=    new UserServiceProxy1(userService);
        IUserService userService1 = (IUserService) userServiceProxy1.getInstance();
        userServiceProxy1.setProxy(userService1);
        System.out.println("Real class："+userService.getClass().getName());
        System.out.println("Proxy class:"+userService1.getClass().getName());
        System.out.println(userService instanceof  IUserService);
        System.out.println(userService1 instanceof  IUserService);
        System.out.println(userService instanceof Proxy);
        System.out.println(userService1 instanceof  Proxy);
        System.out.println("Thread:"+Thread.currentThread().getName()+",name:"+userService1.getNameById());
    }
}
