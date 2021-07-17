package com.rbittencourt.pa.orderreceiver.application.order;

public class EcommerceOrderRequest {

    private String clientId;

    private String paymentPlan;

    private String products;

    public EcommerceOrderRequest() {
    }

    public EcommerceOrderRequest(String clientId, String paymentPlan, String products) {
        this.clientId = clientId;
        this.paymentPlan = paymentPlan;
        this.products = products;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public String getProducts() {
        return products;
    }

}
