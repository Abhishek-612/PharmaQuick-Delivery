package com.example.pharmaquickdelivery;

import java.io.Serializable;

public class Order implements Serializable {


    private String orderId,name,customerAddress,storeName,storeAddress,customerContact,storeContact;
    private double cost;


    public Order(String orderId, String name, String customerAddress, String customerContact) {
        this.orderId = orderId;
        this.name = name;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Order(String orderId, String name, String customerAddress, String storeName, String storeAddress, String customerContact, String storeContact, double cost) {
        this.orderId = orderId;
        this.name = name;
        this.customerAddress = customerAddress;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.customerContact = customerContact;
        this.storeContact = storeContact;
        this.cost = cost;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
