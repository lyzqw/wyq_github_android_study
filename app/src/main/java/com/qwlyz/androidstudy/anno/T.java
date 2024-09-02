package com.qwlyz.androidstudy.anno;

public class T {


    public void show(){
        new PaymentService(new PayPalProcessor());
        new PaymentService(new StripeProcessor());
    }
}
