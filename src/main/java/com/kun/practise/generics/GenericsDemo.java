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
        //sameClassOfDifferentGenerics();
        MyContainer<String> myContainer = new MyContainer<>();

        myContainer.add("1");
        myContainer.add("2");
        myContainer.add("3");
        myContainer.add("4");
        myContainer.add("5");
        myContainer.add("6");
        myContainer.add("7");
        myContainer.add("8");
        myContainer.add("9");
        myContainer.remove("5");

        MyContainer<Integer> integerMyContainer = new MyContainer<>();
        integerMyContainer.add(1);
        integerMyContainer.add(2);
        integerMyContainer.add(3);
        integerMyContainer.add(4);
        integerMyContainer.add(5);
        integerMyContainer.add(6);
        integerMyContainer.add(7);
        integerMyContainer.add(8);
        integerMyContainer.add(9);
        integerMyContainer.remove(9);

       System.out.println( myContainer instanceof  MyContainer);

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

    /**
     * 生产者（Producer）使用extends，消费者（Consumer）使用super。
     *
     * 如果一个列表即要生产，又要消费，你不能使用泛型通配符声明列表，比如List<Integer>
     *
     * 如果你需要一个列表提供T类型的元素（即你想从列表中读取T类型的元素），你需要把这个列表声明成<? extends T>
     *
     * 如果需要一个列表使用T类型的元素（即你想把T类型的元素加入到列表中）,你需要把这个列表声明成<? super T>，比如List<? super Integer>，
     *
     * 这里限制的是 泛型参数
     *
     *
     * 因此你不能保证从中读取到的元素的类型
     *
     * Collections.copy方法
     * */
    private  void pecs(){
      pe();
      cs();
    }

/**
 *
 *
 *  foo 列表
 *  读：不能保证读取的内容是什么，可能是Integer, Double。 仅仅能保证是Number类型
 *  写操作： 由于不知道列表实际指向的是什么类型，因此不能写入、
 *
 *  此智能保证读取的内容为T 或者T的子类
 *
 * */
    private  void pe(){
        List<? extends Number> foo3 = new ArrayList<Integer>();
        List<? extends Number> foo4 = new ArrayList<Double>();
        Number number = foo3.get(0);
    }
    /**
     *
     *
     *  foo 列表
     *  读取： 只能保证读取的内容Object或Object子类对象，并不知道具体的子类
     *  写操作：
     *  可以写入Integer对象，因为声明的所有列表都支持Integer
     *  不能写入Number对象，也不能插入Object对象，因为可能指向ArrayList<Integer>
     *
     *  此只能保证读取为Object或Object子类对象
     *
     * */
    private void cs(){
        List<? super Integer> foo3 = new ArrayList<Integer>();
        List<? super Integer> foo4 = new ArrayList<Number>();
        List<? super Integer> foo5 = new ArrayList<Object>();
        Object o = foo3.get(0);

        foo4.add(5);
        //error
        //foo5.add(new Object());
    }

}
