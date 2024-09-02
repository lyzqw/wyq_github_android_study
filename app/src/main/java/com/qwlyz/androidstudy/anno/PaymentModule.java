//package com.qwlyz.androidstudy.anno;
//
//import dagger.Module;
//import dagger.Provides;
//import dagger.hilt.InstallIn;
//import dagger.hilt.components.SingletonComponent;
//
//@Module
//@InstallIn(SingletonComponent.class)
//public class PaymentModule {
//
//    @Provides
//    @PayPal
//    PaymentProcessor providePayPalProcessor() {
//        return new PayPalProcessor();
//    }
//
//    @Provides
//    @Stripe
//    PaymentProcessor provideStripeProcessor() {
//        return new StripeProcessor();
//    }
//}
