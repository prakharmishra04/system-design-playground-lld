package com.prakhar.di;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("🚚 Delivering by truck on land");
    }
}
