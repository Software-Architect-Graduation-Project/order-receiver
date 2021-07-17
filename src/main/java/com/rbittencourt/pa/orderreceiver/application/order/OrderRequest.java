package com.rbittencourt.pa.orderreceiver.application.order;

public class OrderRequest {

    private String clientId;

    private String paymentPlan;

    private String products;

    public OrderRequest() {
    }

    public OrderRequest(String clientId, String paymentPlan, String products) {
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
