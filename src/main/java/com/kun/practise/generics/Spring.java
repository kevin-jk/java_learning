package com.kun.practise.generics;

/**
 * Created by jrjiakun on 2018/10/30
 *
 * 如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends)
 如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super)
 如果既要存又要取，那么就不要使用任何通配符。
 */
public class Spring extends Season {

    public static void main(String[]args){
        for(int i=0;i<20;i++){
            System.out.println(mod(i,4));
        }
    }


    private static int mod(int hash,int n){
        return (n-1) & hash ;
    }
}
