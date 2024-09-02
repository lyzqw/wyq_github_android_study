package com.qwlyz.androidstudy.anno;

import javax.inject.Inject;

public class PaymentService {

    private final PaymentProcessor paymentProcessor;

    @Inject
    public PaymentService(@PayPal PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void makePayment() {
        paymentProcessor.processPayment();
    }
}
