package com.kun.learning.java.jvm.brige.generic;

import com.kun.learning.java.jvm.brige.VIP;

/**
 * Created by jrjiakun on 2018/11/30
 */
public class VIPOnlyMerchant extends Merchant<VIP> {
    @Override
    public double actionPrice(double price, VIP customer) {
        return 0.00;
    }
}
