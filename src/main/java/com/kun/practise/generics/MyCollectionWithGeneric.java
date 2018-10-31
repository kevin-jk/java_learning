package com.kun.practise.generics;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrjiakun on 2018/10/30
 *
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 *
 * 前面的R类型不能省略
 */
public class MyCollectionWithGeneric<R> implements MyCollection<R> {

    List<String>[] ls = new ArrayList[10];  // ok
    List<?>[] lss = new ArrayList<?>[10];  //ok
    //List<String>[] ls = new ArrayList<String>[10];  //error
    @Override
    public void add(R data) {

    }

    @Override
    public R remove(R data) {
        return null;
    }


    // 如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法
    public static  <R> R getElement(){
        return null;
    }
    /**
     * 泛型方法
     *
     * public 与 返回之前的T比不可少，表明是一个泛型方法
     *
     * 可以有多个泛型类型
     *
     * */
    public <T> T showlistFirst(List<T> list){
        return null==list?null:list.get(0);
    }


}
