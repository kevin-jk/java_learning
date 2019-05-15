package com.kun.learning.java.design_pattern.tempate;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/6 19:07
 */
public abstract class Purchus {
    protected abstract void addThing2PurchusCar();
    protected abstract void submit();
    protected abstract void pay();

    /**
     * 模板方法一般都加上final
     * Buy method
     * @param  thing  something need to buy.
     * */
    public final void buy(String thing){
        System.out.println("购物开始");
        if(null==thing){
            return;
        }
        addThing2PurchusCar();
        submit();
        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }
        pay();
        sayGoodBay();
        System.out.println("购物完成");
    }

    //定义钩子方法， 客户可以自己实现
    protected void sayGoodBay(){
        System.out.println("ByeBye");
    }
}
