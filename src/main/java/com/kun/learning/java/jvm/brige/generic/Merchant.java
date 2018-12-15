package com.kun.learning.java.jvm.brige.generic;

import com.kun.learning.java.jvm.brige.Customer;

/**
 * Created by jrjiakun on 2018/11/30
 */
class Merchant<T extends Customer> {
    public double actionPrice(double price, T customer) {
      return 0.0;
    }
}
