package com.kun.learning.java.jvm.brige;

import java.util.Random;

/**
 * Created by jrjiakun on 2018/11/30
 */
public class NaiveMerchant extends Merchant {
    @Override
    public Double actionPrice(double price, Customer customer) {
      if(customer.isVIP()){
          return price*vipRate();
      }else {
          return (Double) super.actionPrice(price,customer);
      }
    }

    public double vipRate(){
        return new Random().nextDouble()+0.8d;
    }

    public static void main(String[]args){
        NaiveMerchant naiveMerchant = new NaiveMerchant();
        System.out.println(naiveMerchant.actionPrice(1,new VIP()));

    }
}
