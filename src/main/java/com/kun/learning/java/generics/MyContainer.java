package com.kun.learning.java.generics;

/**
 * Created by jrjiakun on 2018/10/30
 *
 * 泛型类，比较典型的如ArrayList
 *
 * 只能是类类型，不能为基本烈性
 */
public class MyContainer <E>{
    Object[] element;
    private static  int default_size = 8;
    MyContainer(){
        element = new Object[8];
    }
    private int size = 0;
    void add(E data){
        if(null==element){
            element = new Object[default_size];
        }
        if(size==element.length){
            Object[] temp = new Object[default_size+size];
            System.arraycopy(element,0,temp,0,size);
            element = temp;
        }
        element[size++] =data;
    }
    E remove(E data){
        E removeData=null;
        if(null==element){
            throw new NullPointerException("容器为空");
        }
        int pos = -1;
        for(int temp=0;temp<size;temp++){
            if(element[temp].equals(data)){
                pos = temp;
                break;
            }
        }
        if(pos!=-1){
            removeData =(E) element[pos];
            element[pos] = element[size-1];
            element[size-1]=null;
            size--;
        }
        return removeData;
    }
}
