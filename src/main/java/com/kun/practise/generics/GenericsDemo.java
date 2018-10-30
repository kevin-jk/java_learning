package com.kun.practise.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrjiakun on 2018/10/30
 *
 * java 泛型在很多地方用到，特意学习下
 *
 *
 */
public class GenericsDemo {
    public static void main(String[]args){
        sameClassOfDifferentGenerics();
    }

    /**
     * 泛型在编译阶段有效，正确检验泛型结果后，会将泛型相关信息擦除，并且在对象进入和离开方法
     * 的边界处添加类型检查和类型转化的方法
     *
     * 在返回元素的时候进行类型强转
     *
     * 泛型信息不会进入到运行时阶段
     * */
    private static void sameClassOfDifferentGenerics(){
        List<String> stringList = new ArrayList<String>();
        List<Integer> integers = new ArrayList<>();
        if(stringList.getClass().equals(integers.getClass())){
            System.out.println("Same class");
        }
    }
}
