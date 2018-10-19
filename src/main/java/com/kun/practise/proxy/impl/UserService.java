package com.kun.practise.proxy.impl;

import com.kun.practise.proxy.IUserService;

/**
 * Created by jrjiakun on 2018/10/19
 */
public class UserService implements IUserService {
    @Override
    public String getNameById() {
        System.out.println("UserService: getNameById");
        return "DEFAULT_NAME";
    }

    @Override
    public String getInfoById() {
        System.out.println("UserService: getInfoById");
        return "DEFAULT_INFO";
    }
}
