package com.kun.practise.generics;

/**
 * Created by jrjiakun on 2018/10/30
 *
 * 泛型接口
 *
 * 在实现类的时候，有2种选择
 * 1. 传入类型实参
 * 2. 不传入类型实参
 */
public interface MyCollection<T> {
    void add(T data);
    T remove(T data);
}
